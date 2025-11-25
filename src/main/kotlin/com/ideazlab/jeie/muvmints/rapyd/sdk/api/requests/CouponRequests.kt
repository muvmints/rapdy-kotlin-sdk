package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateCouponRequest(
    @JsonProperty("amount_off")
    val amountOff: Double? = null,
    val currency: String? = null,
    val description: String? = null,
    @JsonProperty("discount_duration_in_uses")
    val discountDurationInUses: Int? = null,
    @JsonProperty("discount_valid_until")
    val discountValidUntil: Long? = null,
    @JsonProperty("discount_validity_in_months")
    val discountValidityInMonths: Int? = null,
    val duration: String? = null, // forever | repeating
    @JsonProperty("duration_in_months")
    val durationInMonths: Int? = null,
    // Optional custom ID for the coupon (Rapyd will generate if null)
    val id: String? = null,
    @JsonProperty("max_redemptions")
    val maxRedemptions: Int? = null,
    val metadata: Map<String, Any?>? = null,
    @JsonProperty("percent_off")
    val percentOff: Int? = null,
    @JsonProperty("redeem_by")
    val redeemBy: Long? = null
)

// Per Rapyd docs, update coupon primarily supports metadata changes
@Serdeable
data class UpdateCouponRequest(
    val metadata: Map<String, Any?>? = null,
    val description: String? = null
)
