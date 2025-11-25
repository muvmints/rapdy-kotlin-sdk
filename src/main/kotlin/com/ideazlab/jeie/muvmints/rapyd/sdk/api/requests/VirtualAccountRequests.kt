package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import io.micronaut.serde.annotation.Serdeable
import com.fasterxml.jackson.annotation.JsonProperty

@Serdeable
data class CreateVirtualAccountRequest(
    val country: String,
    val currency: String,
    val ewallet: String,
    val description: String? = null,
    @param:JsonProperty("merchant_reference_id")
    val merchantReferenceId: String? = null,
    val metadata: Map<String, Any?>? = null,
    @param:JsonProperty("requested_currency")
    val requestedCurrency: String? = null
)

@Serdeable
data class SimulateBankTransferToVirtualAccountRequest(
    // Note: Rapyd schema defines this as a string value
    val amount: String,
    val currency: String,
    @param:JsonProperty("issued_bank_account")
    val issuedBankAccount: String
)

@Serdeable
data class UpdateRequestedCurrencyRequest(
    @param:JsonProperty("requesting_currency")
    val requestingCurrency: String?
)
