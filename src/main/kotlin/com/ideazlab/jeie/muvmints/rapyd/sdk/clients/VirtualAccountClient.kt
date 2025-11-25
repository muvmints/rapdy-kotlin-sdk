package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateVirtualAccountRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.SimulateBankTransferToVirtualAccountRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateRequestedCurrencyRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CapabilitiesOfVirtualAccountsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CloseVirtualAccountResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CreateVirtualAccountResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RemitterDetailsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.SimulateBankTransferToVirtualAccountResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.TransactionDetailsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.UpdateRequestedCurrencyResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.VirtualAccountResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface VirtualAccountClient {

    @Post("/v1/virtual_accounts")
    fun createVirtualAccount(
        @Body body: CreateVirtualAccountRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CreateVirtualAccountResponse

    @Post("/v1/virtual_accounts/transactions")
    fun simulateBankTransfer(
        @Body body: SimulateBankTransferToVirtualAccountRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): SimulateBankTransferToVirtualAccountResponse

    @Get("/v1/virtual_accounts/{virtualAccountId}")
    fun retrieveVirtualAccount(
        @PathVariable("virtualAccountId") virtualAccountId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): VirtualAccountResponse

    @Post("/v1/virtual_accounts/{virtualAccountId}")
    fun updateRequestedCurrency(
        @PathVariable("virtualAccountId") virtualAccountId: String,
        @Body body: UpdateRequestedCurrencyRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): UpdateRequestedCurrencyResponse

    @Delete("/v1/virtual_accounts/{virtualAccountId}")
    fun closeVirtualAccount(
        @PathVariable("virtualAccountId") virtualAccountId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CloseVirtualAccountResponse

    @Get("/v1/virtual_accounts/capabilities/{country}")
    fun capabilitiesOfVirtualAccounts(
        @PathVariable("country") country: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CapabilitiesOfVirtualAccountsResponse

    @Get("/v1/issuing/bankaccounts/remitters/{virtualAccountId}/transactions/{transaction_id}")
    fun getRemitterDetails(
        @PathVariable("virtualAccountId") virtualAccountId: String,
        @PathVariable("transaction_id") transactionId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): RemitterDetailsResponse

    @Get("/v1/issuing/bankaccounts/{virtualAccountId}/transactions/{transactionId}")
    fun getTransactionDetails(
        @PathVariable("virtualAccountId") virtualAccountId: String,
        @PathVariable("transactionId") transactionId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): TransactionDetailsResponse
}
