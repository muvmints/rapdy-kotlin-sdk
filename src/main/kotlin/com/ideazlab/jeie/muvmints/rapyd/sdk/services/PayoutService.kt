package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreatePayoutRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.Payout3DSResponseRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListPayoutsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.MassPayoutCreateResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PayoutResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.PayoutClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class PayoutService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: PayoutClient
) : BaseService() {

    fun listPayouts(params: Map<String, String?> = emptyMap()): ListPayoutsResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/payouts")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listPayouts(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun createPayout(body: CreatePayoutRequest): PayoutResponse {
        val path = "/v1/payouts"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createPayout(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getPayout(payoutId: String): PayoutResponse {
        val path = "/v1/payouts/$payoutId"
        val signed = sign("get", path, null, config)
        return client.getPayout(
            payoutId = payoutId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updatePayout(payoutId: String, body: Map<String, Any?>): PayoutResponse {
        val path = "/v1/payouts/$payoutId"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updatePayout(
            payoutId = payoutId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    // Beneficiary
    fun createBeneficiary(body: Map<String, Any?>): PayoutResponse {
        val path = "/v1/payouts/beneficiary"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createBeneficiary(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun validateBeneficiary(body: Map<String, Any?>): PayoutResponse {
        val path = "/v1/payouts/beneficiary/validate"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.validateBeneficiary(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getBeneficiary(beneficiaryId: String): PayoutResponse {
        val path = "/v1/payouts/beneficiary/$beneficiaryId"
        val signed = sign("get", path, null, config)
        return client.getBeneficiary(
            beneficiaryId = beneficiaryId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    // Sender
    fun createSender(body: Map<String, Any?>): PayoutResponse {
        val path = "/v1/payouts/sender"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createSender(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getSender(senderId: String): PayoutResponse {
        val path = "/v1/payouts/sender/$senderId"
        val signed = sign("get", path, null, config)
        return client.getSender(
            senderId = senderId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun deleteSender(senderId: String): PayoutResponse {
        val path = "/v1/payouts/sender/$senderId"
        val signed = sign("delete", path, null, config)
        return client.deleteSender(
            senderId = senderId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    // Confirm/Complete/Response
    fun confirmPayout(payoutToken: String): PayoutResponse {
        val path = "/v1/payouts/confirm/$payoutToken"
        val signed = sign("post", path, null, config)
        return client.confirmPayout(
            payoutToken = payoutToken,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun completePayout(payoutToken: String, amount: String): PayoutResponse {
        val path = "/v1/payouts/complete/$payoutToken/$amount"
        val signed = sign("post", path, null, config)
        return client.completePayout(
            payoutToken = payoutToken,
            amount = amount,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun payout3DSResponse(body: Payout3DSResponseRequest): PayoutResponse {
        val path = "/v1/payouts/response"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.payout3DSResponse(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun returnPayout(payoutId: String): PayoutResponse {
        val path = "/v1/payouts/return/$payoutId"
        val signed = sign("post", path, null, config)
        return client.returnPayout(
            payoutId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun uploadPayoutDocument(payoutId: String, parts: Map<String, Any?>): PayoutResponse {
        // OpenAPI: POST /v1/files/documents/payout/{payout}
        val path = "/v1/files/documents/payout/$payoutId"
        val signed = sign("post", path, null, config)
        return client.uploadPayoutDocument(
            payoutId = payoutId,
            parts = parts,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun listPayoutDocuments(payoutId: String): PayoutResponse {
        val path = "/v1/payouts/$payoutId/documents/"
        val signed = sign("get", path, null, config)
        return client.listPayoutDocuments(
            payoutId = payoutId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun deleteAllPayoutDocuments(payoutId: String): PayoutResponse {
        val path = "/v1/payouts/$payoutId/documents/"
        val signed = sign("delete", path, null, config)
        return client.deleteAllPayoutDocuments(
            payoutId = payoutId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getPayoutDocument(payoutId: String, fileId: String): PayoutResponse {
        val path = "/v1/payouts/$payoutId/documents/$fileId"
        val signed = sign("get", path, null, config)
        return client.getPayoutDocument(
            payoutId = payoutId,
            fileId = fileId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun deletePayoutDocument(payoutId: String, fileId: String): PayoutResponse {
        val path = "/v1/payouts/$payoutId/documents/$fileId"
        val signed = sign("delete", path, null, config)
        return client.deletePayoutDocument(
            payoutId = payoutId,
            fileId = fileId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun createMassPayout(parts: Map<String, Any?>): MassPayoutCreateResponse {
        val path = "/v1/batch_process/files/mass_payout_pci"
        val signed = sign("post", path, null, config)
        return client.createMassPayout(
            parts = parts,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
