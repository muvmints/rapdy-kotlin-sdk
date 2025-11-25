package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class CouponListResponse(
    val status: RapydStatus,
    val data: List<RapydCoupon>
)

@Response
data class CouponResponse(
    val status: RapydStatus,
    val data: RapydCoupon
)

@Response
data class CouponDeleteResponse(
    val status: RapydStatus,
    val data: RapydDeletedItem
)

@Serdeable
data class RapydCoupon(
    val id: String?,
    @JsonProperty("amount_off")
    val amountOff: Double?,
    val currency: String?,
    val description: String?,
    @JsonProperty("discount_duration_in_uses")
    val discountDurationInUses: Int?,
    @JsonProperty("discount_valid_until")
    val discountValidUntil: Long?,
    @JsonProperty("discount_validity_in_months")
    val discountValidityInMonths: Int?,
    val duration: String?,
    @JsonProperty("duration_in_months")
    val durationInMonths: Int?,
    @JsonProperty("max_redemptions")
    val maxRedemptions: Int?,
    val metadata: Map<String, Any?>?,
    @JsonProperty("percent_off")
    val percentOff: Int?,
    @JsonProperty("redeem_by")
    val redeemBy: Long?,
    @JsonProperty("times_redeemed")
    val timesRedeemed: Int?,
    val valid: Boolean?,
    // Timestamps present in other models
    @JsonProperty("created")
    val created: Long? = null
)
