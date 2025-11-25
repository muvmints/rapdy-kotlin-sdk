package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.EscrowWalletRelease
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.ReleaseEscrowRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.EscrowResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.EscrowResponseDataOnly
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.EscrowResponseWrapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.EscrowClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class EscrowServiceTest {
    private val config: RapydConfig = TestUtils.testConfig()
    private val objectMapper: ObjectMapper = TestUtils.objectMapper()
    private val client: EscrowClient = mock()
    private val service = EscrowService(config, objectMapper, client)

    @Test
    fun listEscrowReleases_passesPathArgs_andSignedHeaders() {
        val paymentId = "pay_123"
        val escrowId = "esc_456"
        val stub = EscrowResponseWrapper(
            RapydStatus(null, "OK", null, null, null),
            EscrowResponse(
                amountOnHold = 10.0,
                createdAt = 1L,
                currency = "USD",
                escrowReleases = null,
                id = escrowId,
                lastPaymentCompletion = null,
                payment = paymentId,
                status = "open",
                totalAmountReleased = 0.0,
                updatedAt = 1L
            )
        )

        whenever(client.listEscrowReleases(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.listEscrowReleases(paymentId, escrowId)
        assertEquals(stub, out)

        verify(client).listEscrowReleases(
            paymentId = eq(paymentId),
            escrowId = eq(escrowId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun releaseFundsFromEscrow_passesPathBody_andSignedHeaders() {
        val paymentId = "pay_abc"
        val escrowId = "esc_def"
        val body = ReleaseEscrowRequest(
            ewallets = EscrowWalletRelease(
                ewallet = "ew_1",
                amount = 5.0,
                percentage = null
            )
        )

        val stub = EscrowResponseDataOnly(
            data = EscrowResponse(
                amountOnHold = 5.0,
                createdAt = 1L,
                currency = "USD",
                escrowReleases = null,
                id = escrowId,
                lastPaymentCompletion = null,
                payment = paymentId,
                status = "released",
                totalAmountReleased = 5.0,
                updatedAt = 2L
            )
        )

        whenever(client.releaseFundsFromEscrow(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.releaseFundsFromEscrow(paymentId, escrowId, body)
        assertEquals(stub, out)

        verify(client).releaseFundsFromEscrow(
            paymentId = eq(paymentId),
            escrowId = eq(escrowId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getEscrow_passesPathArgs_andSignedHeaders() {
        val paymentId = "pay_X"
        val escrowId = "esc_Y"
        val stub = EscrowResponseWrapper(
            RapydStatus(null, "OK", null, null, null),
            EscrowResponse(
                amountOnHold = 0.0,
                createdAt = 1L,
                currency = "USD",
                escrowReleases = null,
                id = escrowId,
                lastPaymentCompletion = null,
                payment = paymentId,
                status = "open",
                totalAmountReleased = 0.0,
                updatedAt = 1L
            )
        )

        whenever(client.getEscrow(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.getEscrow(paymentId, escrowId)
        assertEquals(stub, out)

        verify(client).getEscrow(
            paymentId = eq(paymentId),
            escrowId = eq(escrowId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
