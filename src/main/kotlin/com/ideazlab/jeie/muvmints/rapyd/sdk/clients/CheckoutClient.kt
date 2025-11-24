package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateCheckoutPageRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CheckoutPageResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface CheckoutClient {

    @Post("/v1/checkout")
    fun createCheckoutPage(
        @Body body: CreateCheckoutPageRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CheckoutPageResponse

    @Get("/v1/checkout/{checkoutToken}")
    fun retrieveCheckoutPage(
        checkoutToken: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CheckoutPageResponse
}
