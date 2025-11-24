package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.ReleaseEscrowRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.EscrowResponseDataOnly
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.EscrowResponseWrapper
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface EscrowClient {

    @Get("/v1/payments/{paymentId}/escrows/{escrowId}/escrow_releases")
    fun listEscrowReleases(
        paymentId: String,
        escrowId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): EscrowResponseWrapper

    @Post("/v1/payments/{paymentId}/escrows/{escrowId}/escrow_releases")
    fun releaseFundsFromEscrow(
        paymentId: String,
        escrowId: String,
        @Body body: ReleaseEscrowRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): EscrowResponseDataOnly

    @Get("/v1/payments/{paymentId}/escrows/{escrowId}")
    fun getEscrow(
        paymentId: String,
        escrowId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): EscrowResponseWrapper
}
