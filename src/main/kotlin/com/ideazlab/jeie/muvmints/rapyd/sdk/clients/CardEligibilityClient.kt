package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CardEligibilityRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CardEligibilityResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface CardEligibilityClient {

    @Post("/v1/cards/eligibility")
    fun cardEligibility(
        @Body body: CardEligibilityRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CardEligibilityResponse
}
