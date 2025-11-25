package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydCustomerAddress

data class UpdatePaymentRequest(
    val address: RapydCustomerAddress? = null,
    val description: String? = null,
    val escrow: Boolean? = null,
    @param:JsonProperty("escrow_release_days")
    val escrowReleaseDays: Int? = null,
    @param:JsonProperty("initiation_type")
    val initiationType: String? = null,
    val metadata: Map<String, Any?>? = null,
    @param:JsonProperty("receipt_email")
    val receiptEmail: String? = null
)
