package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.ReleaseEscrowRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.EscrowResponseDataOnly
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.EscrowResponseWrapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.EscrowClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class EscrowService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: EscrowClient
): BaseService() {

    fun listEscrowReleases(paymentId: String, escrowId: String): EscrowResponseWrapper {
        val path = "/v1/payments/$paymentId/escrows/$escrowId/escrow_releases"
        val signed = sign("get", path, null, config)
        return client.listEscrowReleases(
            paymentId = paymentId,
            escrowId = escrowId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun releaseFundsFromEscrow(paymentId: String, escrowId: String, body: ReleaseEscrowRequest): EscrowResponseDataOnly {
        val path = "/v1/payments/$paymentId/escrows/$escrowId/escrow_releases"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.releaseFundsFromEscrow(
            paymentId = paymentId,
            escrowId = escrowId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getEscrow(paymentId: String, escrowId: String): EscrowResponseWrapper {
        val path = "/v1/payments/$paymentId/escrows/$escrowId"
        val signed = sign("get", path, null, config)
        return client.getEscrow(
            paymentId = paymentId,
            escrowId = escrowId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
