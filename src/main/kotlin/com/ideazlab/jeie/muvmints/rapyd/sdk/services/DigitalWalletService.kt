package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.ApplePaySessionRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ApplePaySessionResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.DigitalWalletClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class DigitalWalletService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: DigitalWalletClient
) : BaseService() {

    fun retrieveApplePaySession(displayName: String, initiativeContext: String): ApplePaySessionResponse {
        val body = ApplePaySessionRequest(displayName = displayName, initiativeContext = initiativeContext)
        return retrieveApplePaySession(body)
    }

    fun retrieveApplePaySession(body: ApplePaySessionRequest): ApplePaySessionResponse {
        val path = "/v1/digital_wallets/session/apple_pay"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.getApplePaySession(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
