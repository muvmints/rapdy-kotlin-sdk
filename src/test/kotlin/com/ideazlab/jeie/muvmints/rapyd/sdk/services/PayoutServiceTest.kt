package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreatePayoutRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.Payout3DSResponseRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListPayoutsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.MassPayoutCreateResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PayoutResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.PayoutClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class PayoutServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: PayoutClient = mock()
    private val service = PayoutService(config, objectMapper, client)

    @Test
    fun listPayouts_filtersAndSortsParams_andPassesSignedHeaders() {
        val input = mapOf(
            "z" to "9",
            "a" to "1",
            "empty" to "",
            "blank" to "   ",
            "nullish" to null
        )
        val expected = linkedMapOf("a" to "1", "z" to "9")
        val stub = ListPayoutsResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listPayouts(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listPayouts(input)
        assertEquals(stub, res)

        verify(client).listPayouts(
            params = eq(expected as Map<String, String?>),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listPayouts_emptyParams_passesNull() {
        val stub = ListPayoutsResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listPayouts(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        service.listPayouts()

        verify(client).listPayouts(
            params = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createPayout_passesBodyAndHeaders() {
        val body = CreatePayoutRequest(
            amount = 12.34,
            currency = "USD",
            payoutMethodType = "us_ach",
            description = "desc",
            ewallet = "ew_1",
            metadata = mapOf("k" to "v"),
            beneficiary = mapOf("b" to 1),
            beneficiaryId = null,
            sender = mapOf("s" to true),
            senderId = null,
            statementDescriptor = "STAT",
            confirmAutomatically = true
        )
        val stub = PayoutResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("id" to "po_1", "status" to "created")
        )
        whenever(client.createPayout(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createPayout(body)
        assertEquals(stub, res)

        verify(client).createPayout(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getPayout_passesHeaders() {
        val payoutId = "po_123"
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("id" to payoutId))
        whenever(client.getPayout(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getPayout(payoutId)
        assertEquals(stub, res)

        verify(client).getPayout(
            payoutId = eq(payoutId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updatePayout_passesBodyAndHeaders() {
        val payoutId = "po_456"
        val body = mapOf("note" to "update", "flag" to true)
        val stub = PayoutResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("id" to payoutId, "note" to "update")
        )
        whenever(client.updatePayout(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updatePayout(payoutId, body)
        assertEquals(stub, res)

        verify(client).updatePayout(
            payoutId = eq(payoutId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createBeneficiary_passesBodyAndHeaders() {
        val body = mapOf("name" to "John B", "country" to "US")
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = body)
        whenever(client.createBeneficiary(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createBeneficiary(body)
        assertEquals(stub, res)

        verify(client).createBeneficiary(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun validateBeneficiary_passesBodyAndHeaders() {
        val body = mapOf("beneficiary_id" to "bn_1")
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("valid" to true))
        whenever(client.validateBeneficiary(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.validateBeneficiary(body)
        assertEquals(stub, res)

        verify(client).validateBeneficiary(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getBeneficiary_passesHeaders() {
        val beneficiaryId = "bn_123"
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("id" to beneficiaryId))
        whenever(client.getBeneficiary(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getBeneficiary(beneficiaryId)
        assertEquals(stub, res)

        verify(client).getBeneficiary(
            beneficiaryId = eq(beneficiaryId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createSender_passesBodyAndHeaders() {
        val body = mapOf("name" to "Sender X")
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = body)
        whenever(client.createSender(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createSender(body)
        assertEquals(stub, res)

        verify(client).createSender(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getSender_passesHeaders() {
        val senderId = "sd_123"
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("id" to senderId))
        whenever(client.getSender(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getSender(senderId)
        assertEquals(stub, res)

        verify(client).getSender(
            senderId = eq(senderId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deleteSender_passesHeaders() {
        val senderId = "sd_del"
        val stub =
            PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("deleted" to true, "id" to senderId))
        whenever(client.deleteSender(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deleteSender(senderId)
        assertEquals(stub, res)

        verify(client).deleteSender(
            senderId = eq(senderId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun confirmPayout_passesHeaders() {
        val payoutToken = "ptok_123"
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("confirmed" to true))
        whenever(client.confirmPayout(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.confirmPayout(payoutToken)
        assertEquals(stub, res)

        verify(client).confirmPayout(
            payoutToken = eq(payoutToken),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun completePayout_passesPathParamsAndHeaders() {
        val payoutToken = "ptok_456"
        val amount = "10.00"
        val stub = PayoutResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("completed" to true, "amount" to amount)
        )
        whenever(client.completePayout(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.completePayout(payoutToken, amount)
        assertEquals(stub, res)

        verify(client).completePayout(
            payoutToken = eq(payoutToken),
            amount = eq(amount),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun payout3DSResponse_passesBodyAndHeaders() {
        val body = Payout3DSResponseRequest(token = "ptok_3ds", response = mapOf("eci" to "05"))
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("handled" to true))
        whenever(client.payout3DSResponse(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.payout3DSResponse(body)
        assertEquals(stub, res)

        verify(client).payout3DSResponse(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun returnPayout_passesHeaders() {
        val payoutId = "po_ret_1"
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("returned" to true))
        whenever(client.returnPayout(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.returnPayout(payoutId)
        assertEquals(stub, res)

        verify(client).returnPayout(
            payoutId = eq(payoutId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun uploadPayoutDocument_passesPartsAndHeaders() {
        val payoutId = "po_doc_1"
        val parts = mapOf("file" to byteArrayOf(1, 2, 3), "purpose" to "payout")
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("uploaded" to true))
        whenever(client.uploadPayoutDocument(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.uploadPayoutDocument(payoutId, parts)
        assertEquals(stub, res)

        verify(client).uploadPayoutDocument(
            payoutId = eq(payoutId),
            parts = eq(parts),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listPayoutDocuments_passesHeaders() {
        val payoutId = "po_docs_list"
        val stub =
            PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("documents" to emptyList<Any>()))
        whenever(client.listPayoutDocuments(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listPayoutDocuments(payoutId)
        assertEquals(stub, res)

        verify(client).listPayoutDocuments(
            payoutId = eq(payoutId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deleteAllPayoutDocuments_passesHeaders() {
        val payoutId = "po_docs_del_all"
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("deleted_all" to true))
        whenever(client.deleteAllPayoutDocuments(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deleteAllPayoutDocuments(payoutId)
        assertEquals(stub, res)

        verify(client).deleteAllPayoutDocuments(
            payoutId = eq(payoutId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getPayoutDocument_passesHeaders() {
        val payoutId = "po_doc_get"
        val fileId = "file_1"
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("file_id" to fileId))
        whenever(client.getPayoutDocument(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getPayoutDocument(payoutId, fileId)
        assertEquals(stub, res)

        verify(client).getPayoutDocument(
            payoutId = eq(payoutId),
            fileId = eq(fileId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deletePayoutDocument_passesHeaders() {
        val payoutId = "po_doc_del"
        val fileId = "file_del"
        val stub = PayoutResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("deleted" to true))
        whenever(client.deletePayoutDocument(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deletePayoutDocument(payoutId, fileId)
        assertEquals(stub, res)

        verify(client).deletePayoutDocument(
            payoutId = eq(payoutId),
            fileId = eq(fileId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createMassPayout_passesPartsAndHeaders() {
        val parts = mapOf("file" to byteArrayOf(9, 8, 7), "filename" to "mass.csv")
        val stub =
            MassPayoutCreateResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("batch_id" to "mp_1"))
        whenever(client.createMassPayout(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createMassPayout(parts)
        assertEquals(stub, res)

        verify(client).createMassPayout(
            parts = eq(parts),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
