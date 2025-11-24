package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

/**
 * Request body for creating a Group Refund.
 * Matches the `refunds_group_payments_body` schema in rapyd-openapi.yaml.
 */
@Serdeable
data class RefundGroupPaymentRequest(
    val amount: Double? = null,
    @param:JsonProperty("group_payment")
    val groupPayment: String
)
