package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateGroupPaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.RefundGroupPaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.GroupPaymentRefundResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.GroupPaymentResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface GroupPaymentClient {

    @Post("/v1/payments/group_payments")
    fun createGroupPayment(
        @Body body: CreateGroupPaymentRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): GroupPaymentResponse

    @Get("/v1/payments/group_payments/{groupPaymentId}")
    fun retrieveGroupPayment(
        groupPaymentId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): GroupPaymentResponse

    @Delete("/v1/payments/group_payments/{groupPaymentId}")
    fun cancelGroupPayment(
        groupPaymentId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): GroupPaymentResponse

    @Post("/v1/refunds/group_payments")
    fun refundGroupPayment(
        @Body body: RefundGroupPaymentRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): GroupPaymentRefundResponse
}
