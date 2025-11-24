package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreatePaymentLinkRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PaymentLinkResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface PaymentLinkClient {

    @Post("/v1/hosted/collect/payments/")
    fun createPaymentLink(
        @Body body: CreatePaymentLinkRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PaymentLinkResponse

    @Get("/v1/hosted/collect/payments/{paymentLink}")
    fun retrievePaymentLink(
        paymentLink: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PaymentLinkResponse
}
