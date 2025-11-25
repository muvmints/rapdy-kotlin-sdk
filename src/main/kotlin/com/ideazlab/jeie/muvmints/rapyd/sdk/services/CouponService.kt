package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateCouponRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateCouponRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CouponDeleteResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CouponListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CouponResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CouponClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class CouponService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: CouponClient
) : BaseService() {

    fun listCoupons(params: Map<String, String?> = emptyMap()): CouponListResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/coupons")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listCoupons(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun createCoupon(body: CreateCouponRequest): CouponResponse {
        val path = "/v1/coupons"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createCoupon(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveCoupon(couponId: String): CouponResponse {
        val path = "/v1/coupons/$couponId"
        val signed = sign("get", path, null, config)
        return client.retrieveCoupon(
            couponId = couponId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updateCoupon(couponId: String, body: UpdateCouponRequest): CouponResponse {
        val path = "/v1/coupons/$couponId"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updateCoupon(
            couponId = couponId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun deleteCoupon(couponId: String): CouponDeleteResponse {
        val path = "/v1/coupons/$couponId"
        val signed = sign("delete", path, null, config)
        return client.deleteCoupon(
            couponId = couponId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
