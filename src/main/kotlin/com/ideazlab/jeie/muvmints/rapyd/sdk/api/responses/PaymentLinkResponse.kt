package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class PaymentLinkResponse(
    val status: RapydStatus,
    val data: List<RapydPaymentLink>
)

@Serdeable
data class RapydPaymentLink(
    val id: String?,
    val amount: Double?,
    @param:JsonProperty("amount_is_editable")
    val amountIsEditable: Boolean?,
    val country: String?,
    val currency: String?,
    val customer: String?,
    @param:JsonProperty("fixed_side")
    val fixedSide: String?,
    val language: String?,
    @param:JsonProperty("max_payments")
    val maxPayments: Int?,
    @param:JsonProperty("merchant_alias")
    val merchantAlias: String?,
    @param:JsonProperty("merchant_color")
    val merchantColor: String?,
    @param:JsonProperty("merchant_customer_support")
    val merchantCustomerSupport: RapydMerchantCustomerSupport?,
    @param:JsonProperty("merchant_logo")
    val merchantLogo: String?,
    @param:JsonProperty("merchant_privacy_policy")
    val merchantPrivacyPolicy: String?,
    @param:JsonProperty("merchant_reference_id")
    val merchantReferenceId: String?,
    @param:JsonProperty("merchant_website")
    val merchantWebsite: String?,
    @param:JsonProperty("page_expiration")
    val pageExpiration: Long?,
    @param:JsonProperty("requested_currency")
    val requestedCurrency: String?,
    @param:JsonProperty("redirect_url")
    val redirectUrl: String?,
    val status: String?,
    val template: Map<String, Any?>?
)

@Serdeable
data class RapydMerchantCustomerSupport(
    val email: String?,
    val url: String?,
    @param:JsonProperty("phone_number")
    val phoneNumber: String?,
    @param:JsonProperty("merchant_logo")
    val merchantLogo: String?
)
