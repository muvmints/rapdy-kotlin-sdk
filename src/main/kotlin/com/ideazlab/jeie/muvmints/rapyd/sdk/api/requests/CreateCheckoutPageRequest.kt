package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateCheckoutPageRequest(
    val amount: Double,
    val currency: String,
    val country: String? = null,
    val description: String? = null,
    val customer: String? = null,
    @param:JsonProperty("payment_method_types_include")
    val paymentMethodTypesInclude: List<String>? = null,
    @param:JsonProperty("complete_url")
    val completeUrl: String? = null,
    @param:JsonProperty("cancel_url")
    val cancelUrl: String? = null,
    @param:JsonProperty("requested_currency")
    val requestedCurrency: String? = null,
    @param:JsonProperty("fixed_side")
    val fixedSide: String? = null,
    val cart: List<CartItem>? = null,
    val metadata: Map<String, Any?>? = null
)

@Serdeable
data class CartItem(
    val name: String,
    val amount: Double,
    val quantity: Int,
    val image: String? = null,
    @param:JsonProperty("percent_off")
    val percentOff: Double? = null,
    @param:JsonProperty("amount_off")
    val amountOff: Double? = null
)
