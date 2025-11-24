package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CompleteRefundRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateRefundRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateRefundRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListRefundsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RefundResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface RefundClient {

    @Get("/v1/refunds")
    fun listRefunds(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ListRefundsResponse

    @Post("/v1/refunds")
    fun createRefund(
        @Body body: CreateRefundRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): RefundResponse

    @Post("/v1/refunds/complete")
    fun completeRefund(
        @Body body: CompleteRefundRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): RefundResponse

    @Get("/v1/refunds/{refundId}")
    fun retrieveRefund(
        refundId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): RefundResponse

    @Post("/v1/refunds/{refundId}")
    fun updateRefund(
        refundId: String,
        @Body body: UpdateRefundRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): RefundResponse
}
