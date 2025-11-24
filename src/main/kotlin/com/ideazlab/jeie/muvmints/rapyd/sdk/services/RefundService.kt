package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CompleteRefundRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateRefundRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateRefundRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListRefundsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RefundResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.RefundClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class RefundService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: RefundClient
) : BaseService() {

    fun listRefunds(params: Map<String, String?> = emptyMap()): ListRefundsResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/refunds")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listRefunds(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun createRefund(body: CreateRefundRequest): RefundResponse {
        val path = "/v1/refunds"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createRefund(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun completeRefund(token: String): RefundResponse {
        val path = "/v1/refunds/complete"
        val body = CompleteRefundRequest(token)
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.completeRefund(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveRefund(refundId: String): RefundResponse {
        val path = "/v1/refunds/$refundId"
        val signed = sign("get", path, null, config)
        return client.retrieveRefund(
            refundId = refundId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updateRefund(refundId: String, metadata: Map<String, Any?>): RefundResponse {
        val path = "/v1/refunds/$refundId"
        val body = UpdateRefundRequest(metadata = metadata)
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updateRefund(
            refundId = refundId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
