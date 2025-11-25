package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CardEligibilityRequest(
    @param:JsonProperty("card_number")
    val cardNumber: String,
    @param:JsonProperty("transaction_type")
    val transactionType: String
)
