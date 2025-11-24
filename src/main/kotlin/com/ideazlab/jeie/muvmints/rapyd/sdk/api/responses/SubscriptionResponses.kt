package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class SubscriptionResponse(
    val status: RapydStatus,
    val data: RapydSubscription
)

@Response
data class SubscriptionListResponse(
    val status: RapydStatus,
    val data: List<RapydSubscription>
)

@Response
data class SubscriptionHostedPageResponse(
    val status: RapydStatus,
    val data: RapydSubscriptionHostedPage
)

@Serdeable
data class RapydSubscription(
    val id: String,
    val status: String?,
    val customer: String?,
    val currency: String?,
    val description: String?,
    val items: Any?,
    @param:JsonProperty("current_period_start")
    val currentPeriodStart: Long?,
    @param:JsonProperty("current_period_end")
    val currentPeriodEnd: Long?,
    @param:JsonProperty("cancel_at_period_end")
    val cancelAtPeriodEnd: Boolean?,
    @param:JsonProperty("created_at")
    val createdAt: Long?
)

@Serdeable
data class RapydSubscriptionHostedPage(
    val id: String?,
    val status: String?,
    @param:JsonProperty("redirect_url")
    val redirectUrl: String?,
    @param:JsonProperty("complete_checkout_url")
    val completeCheckoutUrl: String?,
    @param:JsonProperty("cancel_checkout_url")
    val cancelCheckoutUrl: String?,
    @param:JsonProperty("error_payment_url")
    val errorPaymentUrl: String?,
    @param:JsonProperty("page_expiration")
    val pageExpiration: Long?
)
