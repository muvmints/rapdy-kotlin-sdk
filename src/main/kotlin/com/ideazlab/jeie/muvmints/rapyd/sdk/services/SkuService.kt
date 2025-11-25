package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateSkuRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateSkuRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.SkuDeleteResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.SkuListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.SkuResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.SkuClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class SkuService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: SkuClient
) : BaseService() {

    fun listSkus(params: Map<String, String?> = emptyMap()): SkuListResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/skus")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listSkus(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun createSku(body: CreateSkuRequest): SkuResponse {
        val path = "/v1/skus"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createSku(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getSku(skuId: String): SkuResponse {
        val path = "/v1/skus/$skuId"
        val signed = sign("get", path, null, config)
        return client.getSku(
            skuId = skuId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updateSku(skuId: String, body: UpdateSkuRequest): SkuResponse {
        val path = "/v1/skus/$skuId"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updateSku(
            skuId = skuId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun deleteSku(skuId: String): SkuDeleteResponse {
        val path = "/v1/skus/$skuId"
        val signed = sign("delete", path, null, config)
        return client.deleteSku(
            skuId = skuId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
