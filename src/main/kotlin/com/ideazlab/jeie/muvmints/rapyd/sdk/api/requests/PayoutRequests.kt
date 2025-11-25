package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import io.micronaut.serde.annotation.Serdeable
import com.fasterxml.jackson.annotation.JsonProperty

@Serdeable
data class CreatePayoutRequest(
    val amount: Double,
    val currency: String,
    @param:JsonProperty("payout_method_type")
    val payoutMethodType: String,
    val description: String? = null,
    val ewallet: String? = null,
    val metadata: Map<String, Any?>? = null,
    val beneficiary: Map<String, Any?>? = null,
    @param:JsonProperty("beneficiary_id")
    val beneficiaryId: String? = null,
    val sender: Map<String, Any?>? = null,
    @param:JsonProperty("sender_id")
    val senderId: String? = null,
    @param:JsonProperty("statement_descriptor")
    val statementDescriptor: String? = null,
    @param:JsonProperty("confirm_automatically")
    val confirmAutomatically: Boolean? = null,
)

@Serdeable
data class Payout3DSResponseRequest(
    @param:JsonProperty("token")
    val token: String,
    @param:JsonProperty("response")
    val response: Map<String, Any?>
)
