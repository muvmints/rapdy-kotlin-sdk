package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.IssuingActionResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.IssuingItemResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.IssuingListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.IssuingClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class IssuingService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: IssuingClient
) : BaseService() {

    fun displayIssuedCardDetailsToCustomer(cardToken: String, body: Map<String, Any?>): IssuingItemResponse {
        val path = "/v1/hosted/issuing/card_details/$cardToken"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.displayIssuedCardDetailsToCustomer(
            cardToken = cardToken,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun listCards(
        contact: String? = null,
        pageNumber: Number? = null,
        pageSize: Number? = null,
        creationStartDate: Number? = null,
        creationEndDate: Number? = null,
        activationStartDate: Number? = null,
        activationEndDate: Number? = null
    ): IssuingListResponse {
        val params = linkedMapOf<String, String>()
        fun put(name: String, value: Any?) { if (value != null) params[name] = value.toString() }
        put("activation_end_date", activationEndDate)
        put("activation_start_date", activationStartDate)
        put("contact", contact)
        put("creation_end_date", creationEndDate)
        put("creation_start_date", creationStartDate)
        put("page_number", pageNumber)
        put("page_size", pageSize)
        val query = if (params.isNotEmpty()) params.entries.sortedBy { it.key }
            .joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/issuing/cards")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listCards(
            contact = contact,
            pageNumber = pageNumber,
            pageSize = pageSize,
            creationStartDate = creationStartDate,
            creationEndDate = creationEndDate,
            activationStartDate = activationStartDate,
            activationEndDate = activationEndDate,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun createCard(body: Map<String, Any?>): IssuingItemResponse {
        val path = "/v1/issuing/cards"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createCard(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveCard(cardId: String): IssuingItemResponse {
        val path = "/v1/issuing/cards/$cardId"
        val signed = sign("get", path, null, config)
        return client.retrieveCard(
            cardId = cardId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun activateCard(body: Map<String, Any?>): IssuingActionResponse {
        val path = "/v1/issuing/cards/activate"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.activateCard(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun personalizeCard(body: Map<String, Any?>): IssuingActionResponse {
        val path = "/v1/issuing/cards/personalize"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.personalizeCard(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun changeCardStatus(body: Map<String, Any?>): IssuingActionResponse {
        val path = "/v1/issuing/cards/status"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.changeCardStatus(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun listCardTransactions(
        cardId: String,
        endDate: String? = null,
        minAmount: String? = null,
        maxAmount: String? = null,
        merchantNameSearch: String? = null,
        merchantCategoryCode: String? = null,
        transactionStatus: String? = null,
        startDate: String? = null,
        limit: Number? = null
    ): IssuingListResponse {
        val params = linkedMapOf<String, String>()
        fun put(name: String, value: Any?) { if (value != null) params[name] = value.toString() }
        put("end_date", endDate)
        put("limit", limit)
        put("max_amount", maxAmount)
        put("merchant_category_code", merchantCategoryCode)
        put("merchant_name_search", merchantNameSearch)
        put("min_amount", minAmount)
        put("start_date", startDate)
        put("transaction_status", transactionStatus)
        val query = if (params.isNotEmpty()) params.entries.sortedBy { it.key }
            .joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/issuing/cards/").append(cardId).append("/transactions")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listCardTransactions(
            cardId = cardId,
            endDate = endDate,
            minAmount = minAmount,
            maxAmount = maxAmount,
            merchantNameSearch = merchantNameSearch,
            merchantCategoryCode = merchantCategoryCode,
            transactionStatus = transactionStatus,
            startDate = startDate,
            limit = limit,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveCardTransaction(cardId: String, transactionId: String): IssuingItemResponse {
        val path = "/v1/issuing/cards/$cardId/transactions/$transactionId"
        val signed = sign("get", path, null, config)
        return client.retrieveCardTransaction(
            cardId = cardId,
            transactionId = transactionId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun setCardPin(body: Map<String, Any?>): IssuingActionResponse {
        val path = "/v1/issuing/cards/pin"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.setCardPin(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun createGooglePayCardToken(cardId: String, body: Map<String, Any?>): IssuingItemResponse {
        val path = "/v1/issuing/cards/$cardId/card_tokens/google_pay"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createGooglePayCardToken(
            cardId = cardId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun issueVirtualAccountNumber(body: Map<String, Any?>): IssuingItemResponse {
        val path = "/v1/issuing/bankaccounts"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.issueVirtualAccountNumber(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    // ---- Issuing.Simulations ----

    fun simulateBlockCard(body: Map<String, Any?>): IssuingActionResponse {
        val path = "/v1/issuing/cards/simulate_block"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.simulateBlockCard(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun simulateCardAuthorizationEEA(body: Map<String, Any?>): IssuingItemResponse {
        val path = "/v1/issuing/cards/authorization"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.simulateCardAuthorizationEEA(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun simulateCardReversalEEA(body: Map<String, Any?>): IssuingActionResponse {
        val path = "/v1/issuing/cards/reversal"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.simulateCardReversalEEA(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun simulateClearingCardTransactionEEA(body: Map<String, Any?>): IssuingActionResponse {
        val path = "/v1/issuing/cards/clearing"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.simulateClearingCardTransactionEEA(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun simulateCardRefundEEA(body: Map<String, Any?>): IssuingActionResponse {
        val path = "/v1/issuing/cards/refund"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.simulateCardRefundEEA(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun simulateCardAdjustmentEEA(body: Map<String, Any?>): IssuingActionResponse {
        val path = "/v1/issuing/cards/adjustment"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.simulateCardAdjustmentEEA(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
