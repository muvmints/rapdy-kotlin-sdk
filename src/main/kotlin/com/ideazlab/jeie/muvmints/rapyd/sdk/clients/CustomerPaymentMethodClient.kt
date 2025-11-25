package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.AddCustomerPaymentMethodRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateCustomerPaymentMethodRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CustomerPaymentMethodResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CustomerPaymentMethodsListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.DeleteCustomerPaymentMethodResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface CustomerPaymentMethodClient {

    @Get("/v1/customers/{customerId}/payment_methods")
    fun listCustomerPaymentMethods(
        @PathVariable customerId: String,
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CustomerPaymentMethodsListResponse

    @Post("/v1/customers/{customerId}/payment_methods")
    fun addCustomerPaymentMethod(
        @PathVariable customerId: String,
        @Body body: AddCustomerPaymentMethodRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CustomerPaymentMethodResponse

    @Get("/v1/customers/{customerId}/payment_methods/{pmtId}")
    fun retrieveCustomerPaymentMethod(
        @PathVariable customerId: String,
        @PathVariable pmtId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CustomerPaymentMethodResponse

    @Post("/v1/customers/{customerId}/payment_methods/{pmtId}")
    fun updateCustomerPaymentMethod(
        @PathVariable customerId: String,
        @PathVariable pmtId: String,
        @Body body: UpdateCustomerPaymentMethodRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CustomerPaymentMethodResponse

    @Delete("/v1/customers/{customerId}/payment_methods/{pmtId}")
    fun deleteCustomerPaymentMethod(
        @PathVariable customerId: String,
        @PathVariable pmtId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): DeleteCustomerPaymentMethodResponse
}
