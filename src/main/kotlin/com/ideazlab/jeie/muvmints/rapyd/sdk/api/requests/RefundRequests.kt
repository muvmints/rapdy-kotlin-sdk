package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateRefundRequest(
    val amount: Double? = null,
    val currency: String? = null,
    val ewallets: List<String>? = null,
    @param:JsonProperty("merchant_reference_id")
    val merchantReferenceId: String? = null,
    val metadata: Map<String, Any?>? = null,
    val payment: String,
    val reason: String? = null
)

@Serdeable
data class CompleteRefundRequest(
    val token: String
)

@Serdeable
data class UpdateRefundRequest(
    val metadata: Map<String, Any?>? = null
)
