package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateGroupPaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.RefundGroupPaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.GroupPaymentRefundResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.GroupPaymentResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydGroupPayment
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.GroupPaymentClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class GroupPaymentServiceTest {
    private val config: RapydConfig = TestUtils.testConfig()
    private val objectMapper: ObjectMapper = TestUtils.objectMapper()
    private val client: GroupPaymentClient = mock()
    private val service = GroupPaymentService(config, objectMapper, client)

    @Test
    fun createGroupPayment_passesBody_andSignedHeaders() {
        val body = CreateGroupPaymentRequest(
            description = "Group purchase",
            merchantReferenceId = "mref_1",
            metadata = mapOf("k" to "v"),
            payments = listOf(mapOf("amount" to 10.0, "currency" to "USD"))
        )

        val stub = GroupPaymentResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydGroupPayment(
                id = "gp_1",
                amount = null,
                amountToReplace = null,
                cancelReason = null,
                country = null,
                currency = null,
                description = body.description,
                expiration = null,
                merchantReferenceId = body.merchantReferenceId,
                metadata = body.metadata,
                payments = null,
                status = "NEW"
            )
        )
        whenever(client.createGroupPayment(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.createGroupPayment(body)
        assertEquals(stub, out)

        verify(client).createGroupPayment(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveGroupPayment_passesPath_andSignedHeaders() {
        val id = "gp_123"
        val stub = GroupPaymentResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydGroupPayment(
                id = id,
                amount = null,
                amountToReplace = null,
                cancelReason = null,
                country = null,
                currency = null,
                description = null,
                expiration = null,
                merchantReferenceId = null,
                metadata = null,
                payments = null,
                status = "NEW"
            )
        )
        whenever(client.retrieveGroupPayment(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.retrieveGroupPayment(id)
        assertEquals(stub, out)

        verify(client).retrieveGroupPayment(
            groupPaymentId = eq(id),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun cancelGroupPayment_passesPath_andSignedHeaders() {
        val id = "gp_cancel"
        val stub = GroupPaymentResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydGroupPayment(
                id = id,
                amount = null,
                amountToReplace = null,
                cancelReason = null,
                country = null,
                currency = null,
                description = null,
                expiration = null,
                merchantReferenceId = null,
                metadata = null,
                payments = null,
                status = "CANCELED"
            )
        )
        whenever(client.cancelGroupPayment(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.cancelGroupPayment(id)
        assertEquals(stub, out)

        verify(client).cancelGroupPayment(
            groupPaymentId = eq(id),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun refundGroupPayment_passesBody_andSignedHeaders() {
        val body = RefundGroupPaymentRequest(
            amount = 5.0,
            groupPayment = "gp_1"
        )
        val stub = GroupPaymentRefundResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydGroupPayment(
                id = body.groupPayment,
                amount = null,
                amountToReplace = null,
                cancelReason = null,
                country = null,
                currency = null,
                description = null,
                expiration = null,
                merchantReferenceId = null,
                metadata = null,
                payments = null,
                status = "REFUNDED"
            )
        )
        whenever(client.refundGroupPayment(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.refundGroupPayment(body)
        assertEquals(stub, out)

        verify(client).refundGroupPayment(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
