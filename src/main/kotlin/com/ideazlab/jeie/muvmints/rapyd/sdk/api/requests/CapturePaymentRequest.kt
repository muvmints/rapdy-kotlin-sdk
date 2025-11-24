package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty

data class CapturePaymentRequest(
    val amount: Double? = null,
    @param:JsonProperty("receipt_email")
    val receiptEmail: String? = null,
    @param:JsonProperty("statement_descriptor")
    val statementDescriptor: String? = null
)
