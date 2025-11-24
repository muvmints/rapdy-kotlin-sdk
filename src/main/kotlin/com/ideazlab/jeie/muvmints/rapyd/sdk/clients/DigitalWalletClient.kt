package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.ApplePaySessionRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ApplePaySessionResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface DigitalWalletClient {

    @Post("/v1/digital_wallets/session/apple_pay")
    fun getApplePaySession(
        @Body body: ApplePaySessionRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ApplePaySessionResponse
}
