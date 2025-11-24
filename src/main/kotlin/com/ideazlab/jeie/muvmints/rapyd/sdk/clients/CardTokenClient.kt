package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateCardTokenRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CardTokenHostedPageResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface CardTokenClient {

    @Post("/v1/hosted/collect/card/")
    fun createCardTokenHostedPage(
        @Body body: CreateCardTokenRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CardTokenHostedPageResponse
}
