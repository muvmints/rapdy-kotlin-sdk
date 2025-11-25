package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class PlanListResponse(
    val status: RapydStatus,
    val data: List<RapydPlan>
)

@Response
data class PlanResponse(
    val status: RapydStatus,
    val data: RapydPlan
)

@Response
data class PlanDeleteResponse(
    val status: RapydStatus,
    val data: RapydDeletedItem
)

@Serdeable
data class RapydPlan(
    val id: String?,
    val active: Boolean?,
    @param:JsonProperty("aggregate_usage")
    val aggregateUsage: String?,
    val amount: Double?,
    @param:JsonProperty("billing_scheme")
    val billingScheme: String?,
    val currency: String?,
    val interval: String?,
    @param:JsonProperty("interval_count")
    val intervalCount: Int?,
    val metadata: Map<String, Any?>?,
    val nickname: String?,
    val product: String?,
    val tiers: List<Map<String, Any?>>?,
    @param:JsonProperty("tiers_mode")
    val tiersMode: String?,
    @param:JsonProperty("transform_usage")
    val transformUsage: Map<String, Any?>?,
    @param:JsonProperty("trial_period_days")
    val trialPeriodDays: Int?,
    @param:JsonProperty("usage_type")
    val usageType: String?,
    @param:JsonProperty("created_at")
    val createdAt: Long? = null,
    @param:JsonProperty("updated_at")
    val updatedAt: Long? = null
)
