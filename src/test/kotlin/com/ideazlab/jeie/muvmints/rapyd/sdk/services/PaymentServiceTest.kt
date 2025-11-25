package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CapturePaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CompletePaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdatePaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CreatePaymentResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListPaymentsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydCreatePaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydPayment
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.PaymentClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class PaymentServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: PaymentClient = mock()
    private val service = PaymentService(config, objectMapper, client)

    @Test
    fun createPayment_callsClientWithBodyAndHeaders() {
        val body = RapydCreatePaymentRequest(amount = 10.0, currency = "USD", description = "desc")
        val stub = CreatePaymentResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydPayment("p_1", 10.0, "USD", "created", "desc")
        )
        whenever(client.createPayment(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createPayment(body)
        assertEquals(stub, res)

        verify(client).createPayment(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listPayments_filtersAndSortsParams() {
        val input = mapOf("z" to "9", "a" to "1", "empty" to "", "null" to null)
        val expected = linkedMapOf("a" to "1", "z" to "9")
        val stub = ListPaymentsResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listPayments(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listPayments(input)
        assertEquals(stub, res)

        verify(client).listPayments(
            params = eq(expected as Map<String, String?>),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listPayments_emptyParams_passesNull() {
        val stub = ListPaymentsResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listPayments(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        service.listPayments()

        verify(client).listPayments(
            params = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrievePayment_callsClientWithHeaders() {
        val paymentId = "p_123"
        val stub = CreatePaymentResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydPayment(paymentId, 10.0, "USD", "created", null)
        )
        whenever(client.retrievePayment(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrievePayment(paymentId)
        assertEquals(stub, res)

        verify(client).retrievePayment(
            paymentId = eq(paymentId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updatePayment_callsClientWithBodyAndHeaders() {
        val paymentId = "p_456"
        val body = UpdatePaymentRequest(metadata = mapOf("k" to "v"))
        val stub = CreatePaymentResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydPayment(paymentId, 10.0, "USD", "updated", null)
        )
        whenever(client.updatePayment(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updatePayment(paymentId, body)
        assertEquals(stub, res)

        verify(client).updatePayment(
            paymentId = eq(paymentId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun cancelPayment_callsClientWithHeaders() {
        val paymentId = "p_789"
        val stub = CreatePaymentResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydPayment(paymentId, 10.0, "USD", "canceled", null)
        )
        whenever(client.cancelPayment(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.cancelPayment(paymentId)
        assertEquals(stub, res)

        verify(client).cancelPayment(
            paymentId = eq(paymentId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun capturePayment_withBody_callsClientWithBodyAndHeaders() {
        val paymentId = "p_cap1"
        val body = CapturePaymentRequest(amount = 5.0)
        val stub = CreatePaymentResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydPayment(paymentId, 5.0, "USD", "captured", null)
        )
        whenever(client.capturePayment(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.capturePayment(paymentId, body)
        assertEquals(stub, res)

        verify(client).capturePayment(
            paymentId = eq(paymentId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun capturePayment_withoutBody_passesNullBody() {
        val paymentId = "p_cap2"
        val stub = CreatePaymentResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydPayment(paymentId, 0.0, "USD", "captured", null)
        )
        whenever(client.capturePayment(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        service.capturePayment(paymentId)

        verify(client).capturePayment(
            paymentId = eq(paymentId),
            body = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun completePayment_callsClientWithBodyAndHeaders() {
        val body = CompletePaymentRequest(token = "tok_123")
        val stub = CreatePaymentResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydPayment("p_1", 10.0, "USD", "completed", null)
        )
        whenever(client.completePayment(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.completePayment(body)
        assertEquals(stub, res)

        verify(client).completePayment(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
