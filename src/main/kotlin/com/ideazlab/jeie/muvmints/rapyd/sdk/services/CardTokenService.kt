package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateCardTokenRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CardTokenHostedPageResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CardTokenClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class CardTokenService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: CardTokenClient
) : BaseService() {

    fun createCardTokenHostedPage(body: CreateCardTokenRequest): CardTokenHostedPageResponse {
        val path = "/v1/hosted/collect/card/"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createCardTokenHostedPage(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
