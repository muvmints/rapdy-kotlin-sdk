package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

// Based on components/schemas v1_plans_body and plans_planId_body in rapyd-openapi.yaml

@Serdeable
data class CreatePlanRequest(
    val active: Boolean? = null,
    @JsonProperty("aggregate_usage")
    val aggregateUsage: String? = null,
    val amount: Double? = null,
    @JsonProperty("billing_scheme")
    val billingScheme: String? = null,
    val currency: String,
    val id: String? = null,
    val interval: String,
    @JsonProperty("interval_count")
    val intervalCount: Int? = null,
    val metadata: Map<String, Any?>? = null,
    val nickname: String? = null,
    val product: String,
    // OpenAPI describes tiers as an array of objects but leaves it loosely defined in this spec.
    // Use a flexible representation to avoid tight coupling.
    val tiers: List<Map<String, Any?>>? = null,
    @JsonProperty("tiers_mode")
    val tiersMode: String? = null,
    @JsonProperty("transform_usage")
    val transformUsage: Map<String, Any?>? = null,
    @JsonProperty("trial_period_days")
    val trialPeriodDays: Int? = null,
    @JsonProperty("usage_type")
    val usageType: String? = null
)

@Serdeable
data class UpdatePlanRequest(
    val active: Boolean? = null,
    val metadata: Map<String, Any?>? = null,
    val nickname: String? = null
)
