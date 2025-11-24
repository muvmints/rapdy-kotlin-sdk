package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateCheckoutPageRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CheckoutPageResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CheckoutClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class CheckoutService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: CheckoutClient
) : BaseService() {

    fun createCheckoutPage(body: CreateCheckoutPageRequest): CheckoutPageResponse {
        val path = "/v1/checkout"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createCheckoutPage(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveCheckoutPage(checkoutToken: String): CheckoutPageResponse {
        val path = "/v1/checkout/$checkoutToken"
        val signed = sign("get", path, null, config)
        return client.retrieveCheckoutPage(
            checkoutToken = checkoutToken,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
