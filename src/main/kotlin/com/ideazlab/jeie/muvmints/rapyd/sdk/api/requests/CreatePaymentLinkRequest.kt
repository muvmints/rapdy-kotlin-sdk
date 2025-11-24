package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

/**
 * Request body for creating a Payment Link (hosted collect payments).
 * Matches the `collect_payments_body` schema in rapyd-openapi.yaml.
 */
@Serdeable
data class CreatePaymentLinkRequest(
    val amount: String?,
    @param:JsonProperty("amount_is_editable")
    val amountIsEditable: Boolean? = false,
    // Optional parameters for the checkout page - keep generic to allow pass-through
    val checkout: Map<String, Any?>? = null,
    val country: String,
    val currency: String,
    val customer: String? = null,
    @param:JsonProperty("fixed_side")
    val fixedSide: String? = "buy",
    val language: String? = "en",
    @param:JsonProperty("max_payments")
    val maxPayments: Int? = null,
    @param:JsonProperty("merchant_reference_id")
    val merchantReferenceId: String? = null,
    // Items shown on the hosted page; schema lists as required, keep flexible
    val items: List<Map<String, Any?>>? = null,
    @param:JsonProperty("requested_currency")
    val requestedCurrency: String? = null
)
