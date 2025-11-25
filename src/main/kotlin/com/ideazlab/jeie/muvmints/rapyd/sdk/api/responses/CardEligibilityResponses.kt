package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class CardEligibilityResponse(
    val status: RapydStatus,
    val data: RapydCardEligibility
)

@Serdeable
data class RapydCardEligibility(
    val aft: RapydCardEligibilityAft? = null,
    @param:JsonProperty("billing_currency")
    val billingCurrency: String? = null,
    @param:JsonProperty("card_type")
    val cardType: String? = null,
    @param:JsonProperty("issuer_country")
    val issuerCountry: String? = null,
    val oct: RapydCardEligibilityOct? = null,
    @param:JsonProperty("product_type")
    val productType: String? = null,
    val scheme: String? = null
)

@Serdeable
data class RapydCardEligibilityAft(
    val domestic: String? = null,
    val international: String? = null
)

@Serdeable
data class RapydCardEligibilityOct(
    @param:JsonProperty("money_transfer_domestic")
    val moneyTransferDomestic: String? = null,
    @param:JsonProperty("money_transfer_international")
    val moneyTransferInternational: String? = null,
    @param:JsonProperty("non_money_transfer_domestic")
    val nonMoneyTransferDomestic: String? = null,
    @param:JsonProperty("non_money_transfer_international")
    val nonMoneyTransferInternational: String? = null,
    @param:JsonProperty("online_gambling_domestic")
    val onlineGamblingDomestic: String? = null,
    @param:JsonProperty("online_gambling_international")
    val onlineGamblingInternational: String? = null
)
