package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreatePaymentLinkRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PaymentLinkResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.PaymentLinkClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class PaymentLinkService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: PaymentLinkClient
) : BaseService() {

    fun createPaymentLink(body: CreatePaymentLinkRequest): PaymentLinkResponse {
        val path = "/v1/hosted/collect/payments/"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createPaymentLink(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrievePaymentLink(paymentLinkId: String): PaymentLinkResponse {
        val path = "/v1/hosted/collect/payments/$paymentLinkId"
        val signed = sign("get", path, null, config)
        return client.retrievePaymentLink(
            paymentLink = paymentLinkId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
