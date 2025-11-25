package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.IssuingActionResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.IssuingItemResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.IssuingListResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface IssuingClient {

    // Hosted page to display card details
    @Post("/v1/hosted/issuing/card_details/{cardToken}")
    fun displayIssuedCardDetailsToCustomer(
        @PathVariable("cardToken") cardToken: String,
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingItemResponse

    // List issued cards
    @Get("/v1/issuing/cards")
    fun listCards(
        @QueryValue("contact") contact: String?,
        @QueryValue("page_number") pageNumber: Number?,
        @QueryValue("page_size") pageSize: Number?,
        @QueryValue("creation_start_date") creationStartDate: Number?,
        @QueryValue("creation_end_date") creationEndDate: Number?,
        @QueryValue("activation_start_date") activationStartDate: Number?,
        @QueryValue("activation_end_date") activationEndDate: Number?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingListResponse

    // Create issued card
    @Post("/v1/issuing/cards")
    fun createCard(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingItemResponse

    // Retrieve card details
    @Get("/v1/issuing/cards/{cardId}")
    fun retrieveCard(
        @PathVariable("cardId") cardId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingItemResponse

    // Activate issued card
    @Post("/v1/issuing/cards/activate")
    fun activateCard(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingActionResponse

    // Personalize issued card
    @Post("/v1/issuing/cards/personalize")
    fun personalizeCard(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingActionResponse

    // Change card status (block/unblock)
    @Post("/v1/issuing/cards/status")
    fun changeCardStatus(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingActionResponse

    // List transactions for a card
    @Get("/v1/issuing/cards/{cardId}/transactions")
    fun listCardTransactions(
        @PathVariable("cardId") cardId: String,
        @QueryValue("end_date") endDate: String?,
        @QueryValue("min_amount") minAmount: String?,
        @QueryValue("max_amount") maxAmount: String?,
        @QueryValue("merchant_name_search") merchantNameSearch: String?,
        @QueryValue("merchant_category_code") merchantCategoryCode: String?,
        @QueryValue("transaction_status") transactionStatus: String?,
        @QueryValue("start_date") startDate: String?,
        @QueryValue("limit") limit: Number?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingListResponse

    // Retrieve a card transaction
    @Get("/v1/issuing/cards/{cardId}/transactions/{transactionId}")
    fun retrieveCardTransaction(
        @PathVariable("cardId") cardId: String,
        @PathVariable("transactionId") transactionId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingItemResponse

    // Set card PIN
    @Post("/v1/issuing/cards/pin")
    fun setCardPin(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingActionResponse

    // Create Google Pay card token for issued card
    @Post("/v1/issuing/cards/{card_id}/card_tokens/google_pay")
    fun createGooglePayCardToken(
        @PathVariable("card_id") cardId: String,
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingItemResponse

    // Issue Virtual Account Number (Issuing bankaccounts)
    @Post("/v1/issuing/bankaccounts")
    fun issueVirtualAccountNumber(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingItemResponse

    // ---- Issuing.Simulations ----

    // Simulate Blocking a Card
    @Post("/v1/issuing/cards/simulate_block")
    fun simulateBlockCard(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingActionResponse

    // Simulate a Card Transaction Authorization Request - EEA
    @Post("/v1/issuing/cards/authorization")
    fun simulateCardAuthorizationEEA(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingItemResponse

    // Simulate a Card Transaction Authorization Reversal - EEA
    @Post("/v1/issuing/cards/reversal")
    fun simulateCardReversalEEA(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingActionResponse

    // Simulate Clearing a Card Transaction - EEA
    @Post("/v1/issuing/cards/clearing")
    fun simulateClearingCardTransactionEEA(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingActionResponse

    // Simulate a Card Refund - EEA
    @Post("/v1/issuing/cards/refund")
    fun simulateCardRefundEEA(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingActionResponse

    // Simulate a Card Transaction Adjustment - EEA
    @Post("/v1/issuing/cards/adjustment")
    fun simulateCardAdjustmentEEA(
        @Body body: Map<String, Any?>,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): IssuingActionResponse
}
