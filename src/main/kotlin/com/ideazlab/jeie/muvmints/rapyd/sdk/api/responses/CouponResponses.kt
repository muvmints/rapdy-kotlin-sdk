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
    @param:JsonProperty("amount_off")
    val amountOff: Double?,
    val currency: String?,
    val description: String?,
    @param:JsonProperty("discount_duration_in_uses")
    val discountDurationInUses: Int?,
    @param:JsonProperty("discount_valid_until")
    val discountValidUntil: Long?,
    @param:JsonProperty("discount_validity_in_months")
    val discountValidityInMonths: Int?,
    val duration: String?,
    @param:JsonProperty("duration_in_months")
    val durationInMonths: Int?,
    @param:JsonProperty("max_redemptions")
    val maxRedemptions: Int?,
    val metadata: Map<String, Any?>?,
    @param:JsonProperty("percent_off")
    val percentOff: Int?,
    @param:JsonProperty("redeem_by")
    val redeemBy: Long?,
    @param:JsonProperty("times_redeemed")
    val timesRedeemed: Int?,
    val valid: Boolean?,
    // Timestamps present in other models
    @param:JsonProperty("created")
    val created: Long? = null
)
