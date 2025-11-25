package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreatePayoutRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.Payout3DSResponseRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListPayoutsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.MassPayoutCreateResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PayoutResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface PayoutClient {

    @Get("/v1/payouts")
    fun listPayouts(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ListPayoutsResponse

    @Post("/v1/payouts")
    fun createPayout(
        @Body body: CreatePayoutRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    @Get("/v1/payouts/{payoutId}")
    fun getPayout(
        @PathVariable("payoutId") payoutId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    @Post("/v1/payouts/{payoutId}")
    fun updatePayout(
        @PathVariable("payoutId") payoutId: String,
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    // Beneficiary
    @Post("/v1/payouts/beneficiary")
    fun createBeneficiary(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    @Post("/v1/payouts/beneficiary/validate")
    fun validateBeneficiary(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    @Get("/v1/payouts/beneficiary/{beneficiaryId}")
    fun getBeneficiary(
        @PathVariable("beneficiaryId") beneficiaryId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    // Sender
    @Post("/v1/payouts/sender")
    fun createSender(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    @Get("/v1/payouts/sender/{senderId}")
    fun getSender(
        @PathVariable("senderId") senderId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    @Delete("/v1/payouts/sender/{senderId}")
    fun deleteSender(
        @PathVariable("senderId") senderId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    // Confirm/Complete/Response
    @Post("/v1/payouts/confirm/{payoutToken}")
    fun confirmPayout(
        @PathVariable("payoutToken") payoutToken: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    @Post("/v1/payouts/complete/{payoutToken}/{amount}")
    fun completePayout(
        @PathVariable("payoutToken") payoutToken: String,
        @PathVariable("amount") amount: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    @Post("/v1/payouts/response")
    fun payout3DSResponse(
        @Body body: Payout3DSResponseRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    // Return payout
    @Post("/v1/payouts/return/{payout}")
    fun returnPayout(
        @PathVariable("payout") payoutId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    // Documents (Disburse.Upload Documents)
    // Upload one or multiple documents to a payout
    @Post(value = "/v1/files/documents/payout/{payout}", consumes = [MediaType.MULTIPART_FORM_DATA])
    fun uploadPayoutDocument(
        @PathVariable("payout") payoutId: String,
        @Body parts: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    // List all documents of a payout
    @Get("/v1/payouts/{payout}/documents/")
    fun listPayoutDocuments(
        @PathVariable("payout") payoutId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    // Delete all documents of a payout
    @Delete("/v1/payouts/{payout}/documents/")
    fun deleteAllPayoutDocuments(
        @PathVariable("payout") payoutId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    // Retrieve a specific document of a payout
    @Get("/v1/payouts/{payout}/documents/{file_id}")
    fun getPayoutDocument(
        @PathVariable("payout") payoutId: String,
        @PathVariable("file_id") fileId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    @Delete("/v1/payouts/{payout}/documents/{file_id}")
    fun deletePayoutDocument(
        @PathVariable("payout") payoutId: String,
        @PathVariable("file_id") fileId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PayoutResponse

    // Mass payout (simple placeholder; actual API expects file upload via batch process service)
    @Post(value = "/v1/batch_process/files/mass_payout_pci", consumes = [MediaType.MULTIPART_FORM_DATA])
    fun createMassPayout(
        @Body parts: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): MassPayoutCreateResponse
}
