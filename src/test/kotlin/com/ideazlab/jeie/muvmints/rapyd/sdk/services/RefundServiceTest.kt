package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CompleteRefundRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateRefundRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateRefundRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListRefundsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RefundResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydRefund
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.RefundClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class RefundServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: RefundClient = mock()
    private val service = RefundService(config, objectMapper, client)

    @Test
    fun listRefunds_filtersAndSortsParams() {
        val input = mapOf(
            "z" to "9",
            "a" to "1",
            "empty" to "",
            "blank" to "   ",
            "nullish" to null
        )

        val expected = linkedMapOf("a" to "1", "z" to "9")
        val stub = ListRefundsResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listRefunds(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listRefunds(input)
        assertEquals(stub, res)

        verify(client).listRefunds(
            params = eq(expected as Map<String, String?>),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listRefunds_emptyParams_passesNull() {
        val stub = ListRefundsResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listRefunds(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        service.listRefunds()

        verify(client).listRefunds(
            params = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createRefund_passesBodyAndHeaders() {
        val body =
            CreateRefundRequest(amount = 5.0, currency = "USD", payment = "p_123", reason = "requested_by_customer")
        val stub = RefundResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydRefund(
                id = "rf_1",
                amount = 5.0,
                currency = "USD",
                createdAt = null,
                ewallets = null,
                failureReason = null,
                fixedSide = null,
                fxRate = null,
                merchantDebitedAmount = null,
                merchantDebitedCurrency = null,
                merchantReferenceId = null,
                metadata = null,
                payment = body.payment,
                paymentCreatedAt = null,
                paymentMethodType = null,
                proportionalRefund = null,
                reason = body.reason,
                receiptNumber = null,
                status = "created",
                updatedAt = null
            )
        )
        whenever(client.createRefund(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createRefund(body)
        assertEquals(stub, res)

        verify(client).createRefund(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun completeRefund_wrapsTokenIntoBody_andPassesHeaders() {
        val token = "tok_refund_123"
        val expectedBody = CompleteRefundRequest(token)
        val stub = RefundResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydRefund(
                id = "rf_2",
                amount = null,
                currency = null,
                createdAt = null,
                ewallets = null,
                failureReason = null,
                fixedSide = null,
                fxRate = null,
                merchantDebitedAmount = null,
                merchantDebitedCurrency = null,
                merchantReferenceId = null,
                metadata = null,
                payment = null,
                paymentCreatedAt = null,
                paymentMethodType = null,
                proportionalRefund = null,
                reason = null,
                receiptNumber = null,
                status = "completed",
                updatedAt = null
            )
        )
        whenever(client.completeRefund(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.completeRefund(token)
        assertEquals(stub, res)

        verify(client).completeRefund(
            body = eq(expectedBody),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveRefund_passesHeaders() {
        val refundId = "rf_3"
        val stub = RefundResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydRefund(
                id = refundId,
                amount = null,
                currency = null,
                createdAt = null,
                ewallets = null,
                failureReason = null,
                fixedSide = null,
                fxRate = null,
                merchantDebitedAmount = null,
                merchantDebitedCurrency = null,
                merchantReferenceId = null,
                metadata = null,
                payment = null,
                paymentCreatedAt = null,
                paymentMethodType = null,
                proportionalRefund = null,
                reason = null,
                receiptNumber = null,
                status = null,
                updatedAt = null
            )
        )
        whenever(client.retrieveRefund(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveRefund(refundId)
        assertEquals(stub, res)

        verify(client).retrieveRefund(
            refundId = eq(refundId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updateRefund_wrapsMetadataIntoBody_andPassesHeaders() {
        val refundId = "rf_4"
        val metadata = mapOf("k" to "v")
        val expectedBody = UpdateRefundRequest(metadata = metadata)
        val stub = RefundResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydRefund(
                id = refundId,
                amount = null,
                currency = null,
                createdAt = null,
                ewallets = null,
                failureReason = null,
                fixedSide = null,
                fxRate = null,
                merchantDebitedAmount = null,
                merchantDebitedCurrency = null,
                merchantReferenceId = null,
                metadata = metadata,
                payment = null,
                paymentCreatedAt = null,
                paymentMethodType = null,
                proportionalRefund = null,
                reason = null,
                receiptNumber = null,
                status = "updated",
                updatedAt = null
            )
        )
        whenever(client.updateRefund(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updateRefund(refundId, metadata)
        assertEquals(stub, res)

        verify(client).updateRefund(
            refundId = eq(refundId),
            body = eq(expectedBody),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
