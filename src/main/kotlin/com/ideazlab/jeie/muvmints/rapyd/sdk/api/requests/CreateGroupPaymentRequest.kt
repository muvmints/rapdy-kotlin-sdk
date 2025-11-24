package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

/**
 * Request body for creating a Group Payment.
 * Matches the `payments_group_payments_body` schema in rapyd-openapi.yaml.
 */
@Serdeable
data class CreateGroupPaymentRequest(
    val description: String? = null,
    @param:JsonProperty("merchant_reference_id")
    val merchantReferenceId: String? = null,
    val metadata: Map<String, Any?>? = null,
    // The schema describes this as an array of payment objects; keep it flexible
    // to allow passing through payment fields supported by Rapyd.
    val payments: List<Map<String, Any?>>
)
