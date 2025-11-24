package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreatePlanRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdatePlanRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PlanDeleteResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PlanListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PlanResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.PlanClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class PlanService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: PlanClient
) : BaseService() {

    fun listPlans(params: Map<String, String?> = emptyMap()): PlanListResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/plans")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listPlans(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun createPlan(body: CreatePlanRequest): PlanResponse {
        val path = "/v1/plans"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createPlan(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrievePlan(planId: String): PlanResponse {
        val path = "/v1/plans/$planId"
        val signed = sign("get", path, null, config)
        return client.retrievePlan(
            planId = planId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updatePlan(planId: String, body: UpdatePlanRequest): PlanResponse {
        val path = "/v1/plans/$planId"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updatePlan(
            planId = planId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun deletePlan(planId: String): PlanDeleteResponse {
        val path = "/v1/plans/$planId"
        val signed = sign("delete", path, null, config)
        return client.deletePlan(
            planId = planId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
