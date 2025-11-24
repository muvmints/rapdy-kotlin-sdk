package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateSubscriptionRequest(
    val customer: String,
    val currency: String?,
    val billing: String? = null,
    val coupon: String? = null,
    val description: String? = null,
    val items: List<Map<String, Any?>>? = null,
    val metadata: Map<String, Any?>? = null,
    @param:JsonProperty("trial_period_days")
    val trialPeriodDays: Int? = null,
    @param:JsonProperty("trial_end")
    val trialEnd: Any? = null
)

@Serdeable
data class UpdateSubscriptionRequest(
    val billing: String? = null,
    val coupon: String? = null,
    val description: String? = null,
    val items: List<Map<String, Any?>>? = null,
    val metadata: Map<String, Any?>? = null,
    @param:JsonProperty("cancel_at_period_end")
    val cancelAtPeriodEnd: Boolean? = null,
    @param:JsonProperty("trial_end")
    val trialEnd: Any? = null
)

@Serdeable
data class CreateSubscriptionByHostedPageRequest(
    val customer: String,
    val currency: String?,
    val items: List<Map<String, Any?>>? = null,
    @param:JsonProperty("complete_checkout_url")
    val completeCheckoutUrl: String? = null,
    @param:JsonProperty("cancel_checkout_url")
    val cancelCheckoutUrl: String? = null,
    @param:JsonProperty("error_payment_url")
    val errorPaymentUrl: String? = null,
    @param:JsonProperty("complete_payment_url")
    val completePaymentUrl: String? = null,
    val metadata: Map<String, Any?>? = null
)
