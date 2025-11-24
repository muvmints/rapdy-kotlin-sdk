package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.DisputeResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListDisputesResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface DisputeClient {

    @Get("/v1/disputes")
    fun listDisputes(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ListDisputesResponse

    @Get("/v1/disputes/{disputeId}")
    fun retrieveDispute(
        disputeId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): DisputeResponse
}
