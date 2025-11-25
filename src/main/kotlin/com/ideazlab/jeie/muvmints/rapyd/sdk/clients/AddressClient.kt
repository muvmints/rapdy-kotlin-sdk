package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.RapydAddressRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.AddressResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface AddressClient {

    @Post("/v1/addresses")
    fun createAddress(
        @Body body: RapydAddressRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): AddressResponse

    @Get("/v1/addresses/{addressId}")
    fun retrieveAddress(
        @PathVariable addressId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): AddressResponse

    @Post("/v1/addresses/{addressId}")
    fun updateAddress(
        @PathVariable addressId: String,
        @Body body: RapydAddressRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): AddressResponse

    @Delete("/v1/addresses/{addressId}")
    fun deleteAddress(
        @PathVariable addressId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): AddressResponse
}
