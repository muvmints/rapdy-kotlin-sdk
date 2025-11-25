package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreatePlanRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdatePlanRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.PlanClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class PlanServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: PlanClient = mock()
    private val service = PlanService(config, objectMapper, client)

    @Test
    fun listPlans_filtersAndSortsParams() {
        val input = mapOf("b" to "2", "a" to "1", "empty" to "", "null" to null)
        val expected = linkedMapOf("a" to "1", "b" to "2")
        val stub = PlanListResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listPlans(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listPlans(input)
        assertEquals(stub, res)

        verify(client).listPlans(
            params = eq(expected as Map<String, String?>),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listPlans_emptyParams_passesNull() {
        val stub = PlanListResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listPlans(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        service.listPlans()

        verify(client).listPlans(
            params = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createPlan_passesBodyAndHeaders() {
        val body = CreatePlanRequest(product = "prod_1", interval = "month", currency = "USD", amount = 9.99)
        val stub = PlanResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydPlan(
                id = "pl_1",
                active = null,
                aggregateUsage = null,
                amount = 9.99,
                billingScheme = null,
                currency = "USD",
                interval = "month",
                intervalCount = null,
                metadata = null,
                nickname = null,
                product = "prod_1",
                tiers = null,
                tiersMode = null,
                transformUsage = null,
                trialPeriodDays = null,
                usageType = null,
                createdAt = null,
                updatedAt = null
            )
        )
        whenever(client.createPlan(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createPlan(body)
        assertEquals(stub, res)

        verify(client).createPlan(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrievePlan_passesHeaders() {
        val planId = "pl_1"
        val stub = PlanResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydPlan(
                id = planId,
                active = null,
                aggregateUsage = null,
                amount = 9.99,
                billingScheme = null,
                currency = "USD",
                interval = "month",
                intervalCount = null,
                metadata = null,
                nickname = null,
                product = "prod_1",
                tiers = null,
                tiersMode = null,
                transformUsage = null,
                trialPeriodDays = null,
                usageType = null,
                createdAt = null,
                updatedAt = null
            )
        )
        whenever(client.retrievePlan(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrievePlan(planId)
        assertEquals(stub, res)

        verify(client).retrievePlan(
            planId = eq(planId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updatePlan_passesBodyAndHeaders() {
        val planId = "pl_2"
        val body = UpdatePlanRequest(metadata = mapOf("a" to "b"))
        val stub = PlanResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydPlan(
                id = planId,
                active = null,
                aggregateUsage = null,
                amount = 9.99,
                billingScheme = null,
                currency = "USD",
                interval = "month",
                intervalCount = null,
                metadata = null,
                nickname = null,
                product = "prod_1",
                tiers = null,
                tiersMode = null,
                transformUsage = null,
                trialPeriodDays = null,
                usageType = null,
                createdAt = null,
                updatedAt = null
            )
        )
        whenever(client.updatePlan(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updatePlan(planId, body)
        assertEquals(stub, res)

        verify(client).updatePlan(
            planId = eq(planId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deletePlan_passesHeaders() {
        val planId = "pl_3"
        val stub =
            PlanDeleteResponse(RapydStatus(null, "OK", null, null, null), RapydDeletedItem(deleted = true, id = planId))
        whenever(client.deletePlan(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deletePlan(planId)
        assertEquals(stub, res)

        verify(client).deletePlan(
            planId = eq(planId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
