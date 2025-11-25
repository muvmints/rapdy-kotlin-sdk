package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.RapydAddressRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.AddressResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.AddressClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class AddressService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: AddressClient
) : BaseService() {

    fun createAddress(body: RapydAddressRequest): AddressResponse {
        val path = "/v1/addresses"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createAddress(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveAddress(addressId: String): AddressResponse {
        val path = "/v1/addresses/$addressId"
        val signed = sign("get", path, null, config)
        return client.retrieveAddress(
            addressId = addressId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updateAddress(addressId: String, body: RapydAddressRequest): AddressResponse {
        val path = "/v1/addresses/$addressId"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updateAddress(
            addressId = addressId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun deleteAddress(addressId: String): AddressResponse {
        val path = "/v1/addresses/$addressId"
        val signed = sign("delete", path, null, config)
        return client.deleteAddress(
            addressId = addressId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
