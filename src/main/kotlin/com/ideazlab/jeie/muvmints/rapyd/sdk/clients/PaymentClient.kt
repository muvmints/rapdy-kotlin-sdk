package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydCreatePaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CreatePaymentResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListPaymentsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdatePaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CapturePaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CompletePaymentRequest
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Delete
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface PaymentClient {
    @Post("/v1/payments")
    fun createPayment(
        @Body body: RapydCreatePaymentRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CreatePaymentResponse

    @Get("/v1/payments")
    fun listPayments(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ListPaymentsResponse

    @Get("/v1/payments/{paymentId}")
    fun retrievePayment(
        paymentId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CreatePaymentResponse

    @Post("/v1/payments/{paymentId}")
    fun updatePayment(
        paymentId: String,
        @Body body: UpdatePaymentRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CreatePaymentResponse

    @Delete("/v1/payments/{paymentId}")
    fun cancelPayment(
        paymentId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CreatePaymentResponse

    @Post("/v1/payments/{paymentId}/capture")
    fun capturePayment(
        paymentId: String,
        @Body body: CapturePaymentRequest?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CreatePaymentResponse

    @Post("/v1/payments/completePayment")
    fun completePayment(
        @Body body: CompletePaymentRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CreatePaymentResponse
}