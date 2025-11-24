package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreatePlanRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdatePlanRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PlanDeleteResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PlanListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PlanResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface PlanClient {

    @Get("/v1/plans")
    fun listPlans(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PlanListResponse

    @Post("/v1/plans")
    fun createPlan(
        @Body body: CreatePlanRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PlanResponse

    @Get("/v1/plans/{planId}")
    fun retrievePlan(
        @PathVariable("planId") planId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PlanResponse

    @Post("/v1/plans/{planId}")
    fun updatePlan(
        @PathVariable("planId") planId: String,
        @Body body: UpdatePlanRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PlanResponse

    @Delete("/v1/plans/{planId}")
    fun deletePlan(
        @PathVariable("planId") planId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): PlanDeleteResponse
}
