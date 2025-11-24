package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydCreatePaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CreatePaymentResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListPaymentsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.PaymentClient
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdatePaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CapturePaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CompletePaymentRequest
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
@Requires(bean = RapydConfig::class)
class PaymentService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: PaymentClient
): BaseService(){
    fun createPayment(body: RapydCreatePaymentRequest): CreatePaymentResponse {
        val path = "/v1/payments"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createPayment(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun listPayments(params: Map<String, String?> = emptyMap()): ListPaymentsResponse {
        // Build a deterministic query string (sorted by key) for signature and pass LinkedHashMap in same order
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/payments")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listPayments(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrievePayment(paymentId: String): CreatePaymentResponse {
        val path = "/v1/payments/$paymentId"
        val signed = sign("get", path, null, config)
        return client.retrievePayment(
            paymentId = paymentId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updatePayment(paymentId: String, body: UpdatePaymentRequest): CreatePaymentResponse {
        val path = "/v1/payments/$paymentId"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updatePayment(
            paymentId = paymentId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun cancelPayment(paymentId: String): CreatePaymentResponse {
        val path = "/v1/payments/$paymentId"
        val signed = sign("delete", path, null, config)
        return client.cancelPayment(
            paymentId = paymentId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun capturePayment(paymentId: String, body: CapturePaymentRequest? = null): CreatePaymentResponse {
        val path = "/v1/payments/$paymentId/capture"
        val jsonBody = body?.let { objectMapper.writeValueAsString(it).replace("\\/", "/") }
        val signed = sign("post", path, jsonBody, config)
        return client.capturePayment(
            paymentId = paymentId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun completePayment(body: CompletePaymentRequest): CreatePaymentResponse {
        val path = "/v1/payments/completePayment"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.completePayment(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }


    companion object {
        private val LOG = LoggerFactory.getLogger(this::class.java)
    }
}