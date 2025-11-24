package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty

data class CapturePaymentRequest(
    val amount: Double? = null,
    @JsonProperty("receipt_email")
    val receiptEmail: String? = null,
    @JsonProperty("statement_descriptor")
    val statementDescriptor: String? = null
)
