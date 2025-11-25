package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateWalletRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateContactRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ContactListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ContactResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.DeleteContactResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ComplianceLevelsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydCreateWalletRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CreateWalletResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.WalletAccountsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.WalletActionResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.WalletListResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface WalletClient {

    @Get("/v1/ewallets")
    fun listWallets(
        @QueryValue("phone_number") phoneNumber: String?,
        @QueryValue("email") email: String?,
        @QueryValue("ewallet_reference_id") ewalletReferenceId: String?,
        @QueryValue("page_number") pageNumber: Number?,
        @QueryValue("page_size") pageSize: Number?,
        @QueryValue("type") type: String?,
        @QueryValue("min_balance") minBalance: Number?,
        @QueryValue("currency") currency: String?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): WalletListResponse

    
    @Post("/v1/ewallets")
    fun createWallet(
        @Body body: RapydCreateWalletRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CreateWalletResponse

    @Get("/v1/ewallets/{ewalletId}/accounts")
    fun getWalletAccounts(
        @PathVariable ewalletId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): WalletAccountsResponse

    @Get("/v1/ewallets/{ewalletToken}")
    fun retrieveWallet(
        @PathVariable("ewalletToken") ewalletToken: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CreateWalletResponse

    @Post("/v1/ewallets/{ewalletToken}")
    fun updateWallet(
        @PathVariable("ewalletToken") ewalletToken: String,
        @Body body: UpdateWalletRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CreateWalletResponse

    @Delete("/v1/ewallets/{ewalletToken}")
    fun deleteWallet(
        @PathVariable("ewalletToken") ewalletToken: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): WalletActionResponse

    @Post("/v1/ewallets/{ewalletToken}/statuses/{status}")
    fun changeWalletStatus(
        @PathVariable("ewalletToken") ewalletToken: String,
        @PathVariable("status") status: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): WalletActionResponse

    // ---- eWallet.Contact ----

    @Get("/v1/ewallets/{ewalletId}/contacts")
    fun listContacts(
        @PathVariable("ewalletId") ewalletId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ContactListResponse

    @Post("/v1/ewallets/{ewalletId}/contacts")
    fun createContact(
        @PathVariable("ewalletId") ewalletId: String,
        @Body body: CreateContactRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ContactResponse

    @Get("/v1/ewallets/{ewalletId}/contacts/{contactId}")
    fun getContact(
        @PathVariable("ewalletId") ewalletId: String,
        @PathVariable("contactId") contactId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ContactResponse

    @Post("/v1/ewallets/{ewalletId}/contacts/{contactId}")
    fun updateContact(
        @PathVariable("ewalletId") ewalletId: String,
        @PathVariable("contactId") contactId: String,
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ContactResponse

    @Delete("/v1/ewallets/{ewalletId}/contacts/{contactId}")
    fun deleteContact(
        @PathVariable("ewalletId") ewalletId: String,
        @PathVariable("contactId") contactId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): DeleteContactResponse

    @Get("/v1/ewallets/{ewalletId}/contacts/{contactId}/compliance_levels")
    fun getContactComplianceLevels(
        @PathVariable("ewalletId") ewalletId: String,
        @PathVariable("contactId") contactId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ComplianceLevelsResponse
}