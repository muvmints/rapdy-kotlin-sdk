package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydPayment
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class CheckoutPageResponse(
    val status: RapydStatus,
    val data: RapydCheckoutPage
)

@Serdeable
data class RapydCheckoutPage(
    val id: String?,
    val status: String?,
    @param:JsonProperty("redirect_url")
    val redirectUrl: String?,
    @param:JsonProperty("complete_checkout_url")
    val completeCheckoutUrl: String?,
    @param:JsonProperty("complete_payment_url")
    val completePaymentUrl: String?,
    @param:JsonProperty("cancel_checkout_url")
    val cancelCheckoutUrl: String?,
    @param:JsonProperty("error_payment_url")
    val errorPaymentUrl: String?,
    @param:JsonProperty("merchant_website")
    val merchantWebsite: String?,
    @param:JsonProperty("page_expiration")
    val pageExpiration: Long?,
    val payment: List<RapydPayment>?
)
