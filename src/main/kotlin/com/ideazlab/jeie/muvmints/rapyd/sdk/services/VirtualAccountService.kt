package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateVirtualAccountRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.SimulateBankTransferToVirtualAccountRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateRequestedCurrencyRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.VirtualAccountClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class VirtualAccountService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: VirtualAccountClient
) : BaseService() {

    fun createVirtualAccount(body: CreateVirtualAccountRequest): CreateVirtualAccountResponse {
        val path = "/v1/virtual_accounts"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createVirtualAccount(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun simulateBankTransferToVirtualAccount(body: SimulateBankTransferToVirtualAccountRequest): SimulateBankTransferToVirtualAccountResponse {
        val path = "/v1/virtual_accounts/transactions"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.simulateBankTransfer(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveVirtualAccount(virtualAccountId: String): VirtualAccountResponse {
        val path = "/v1/virtual_accounts/$virtualAccountId"
        val signed = sign("get", path, null, config)
        return client.retrieveVirtualAccount(
            virtualAccountId = virtualAccountId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updateRequestedCurrency(virtualAccountId: String, body: UpdateRequestedCurrencyRequest): UpdateRequestedCurrencyResponse {
        val path = "/v1/virtual_accounts/$virtualAccountId"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updateRequestedCurrency(
            virtualAccountId = virtualAccountId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun closeVirtualAccount(virtualAccountId: String): CloseVirtualAccountResponse {
        val path = "/v1/virtual_accounts/$virtualAccountId"
        val signed = sign("delete", path, null, config)
        return client.closeVirtualAccount(
            virtualAccountId = virtualAccountId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun capabilitiesOfVirtualAccounts(country: String): CapabilitiesOfVirtualAccountsResponse {
        val path = "/v1/virtual_accounts/capabilities/$country"
        val signed = sign("get", path, null, config)
        return client.capabilitiesOfVirtualAccounts(
            country = country,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getRemitterDetails(virtualAccountId: String, transactionId: String): RemitterDetailsResponse {
        val path = "/v1/issuing/bankaccounts/remitters/$virtualAccountId/transactions/$transactionId"
        val signed = sign("get", path, null, config)
        return client.getRemitterDetails(
            virtualAccountId = virtualAccountId,
            transactionId = transactionId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getTransactionDetails(virtualAccountId: String, transactionId: String): TransactionDetailsResponse {
        val path = "/v1/issuing/bankaccounts/$virtualAccountId/transactions/$transactionId"
        val signed = sign("get", path, null, config)
        return client.getTransactionDetails(
            virtualAccountId = virtualAccountId,
            transactionId = transactionId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
