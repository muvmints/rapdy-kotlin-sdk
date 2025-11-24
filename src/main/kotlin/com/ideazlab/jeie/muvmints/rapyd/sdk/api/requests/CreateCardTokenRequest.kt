package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateCardTokenRequest(
    @param:JsonProperty("billing_address_collect")
    val billingAddressCollect: Boolean? = null,
    @param:JsonProperty("cancel_url")
    val cancelUrl: String? = null,
    @param:JsonProperty("card_fields")
    val cardFields: CardFields? = null,
    @param:JsonProperty("complete_url")
    val completeUrl: String? = null,
    @param:JsonProperty("complete_payment_url")
    val completePaymentUrl: String? = null,
    val country: String,
    val currency: String? = null,
    val customer: String,
    @param:JsonProperty("error_payment_url")
    val errorPaymentUrl: String? = null,
    val language: String? = null,
    @param:JsonProperty("page_expiration")
    val pageExpiration: Long? = null,
    @param:JsonProperty("payment_method_type")
    val paymentMethodType: String? = null
)

@Serdeable
data class CardFields(
    @param:JsonProperty("recurrence_type")
    val recurrenceType: String? = null
)
