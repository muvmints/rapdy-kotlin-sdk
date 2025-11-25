package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CardEligibilityRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CardEligibilityResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CardEligibilityClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class CardEligibilityService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: CardEligibilityClient
): BaseService() {

    fun retrieveCardEligibility(body: CardEligibilityRequest): CardEligibilityResponse {
        val path = "/v1/cards/eligibility"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.cardEligibility(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
