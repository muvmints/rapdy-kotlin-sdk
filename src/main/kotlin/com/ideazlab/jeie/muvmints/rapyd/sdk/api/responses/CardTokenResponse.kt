package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class CardTokenHostedPageResponse(
    val status: RapydStatus,
    val data: List<RapydCardTokenHostedPage>
)

@Serdeable
data class RapydCardTokenHostedPage(
    @param:JsonProperty("billing_address_collect")
    val billingAddressCollect: Boolean?,
    @param:JsonProperty("cancel_url")
    val cancelUrl: String?,
    @param:JsonProperty("card_fields")
    val cardFields: RapydCardTokenCardFields?,
    val category: String?,
    @param:JsonProperty("complete_url")
    val completeUrl: String?,
    @param:JsonProperty("complete_payment_url")
    val completePaymentUrl: String?,
    val country: String?,
    val currency: String?,
    val customer: String?,
    @param:JsonProperty("error_code")
    val errorCode: String?,
    @param:JsonProperty("error_payment_url")
    val errorPaymentUrl: String?,
    val id: String?,
    val language: String?,
    @param:JsonProperty("merchant_alias")
    val merchantAlias: String?,
    @param:JsonProperty("merchant_color")
    val merchantColor: String?,
    @param:JsonProperty("merchant_customer_support")
    val merchantCustomerSupport: RapydMerchantCustomerSupport?,
    @param:JsonProperty("merchant_website")
    val merchantWebsite: String?,
    @param:JsonProperty("page_expiration")
    val pageExpiration: Long?,
    @param:JsonProperty("payment_method_type")
    val paymentMethodType: String?,
    @param:JsonProperty("payment_params")
    val paymentParams: RapydCardTokenPaymentParams?,
    @param:JsonProperty("redirect_url")
    val redirectUrl: String?
)

@Serdeable
data class RapydCardTokenCardFields(
    @param:JsonProperty("recurrence_type")
    val recurrenceType: String?
)

@Serdeable
data class RapydCardTokenPaymentParams(
    @param:JsonProperty("complete_payment_url")
    val completePaymentUrl: String?,
    @param:JsonProperty("error_payment_url")
    val errorPaymentUrl: String?
)

// RapydMerchantCustomerSupport is already defined in PaymentLinkResponse.kt within the same package.
