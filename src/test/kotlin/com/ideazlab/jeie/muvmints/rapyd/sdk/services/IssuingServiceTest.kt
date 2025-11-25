package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.IssuingActionResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.IssuingItemResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.IssuingListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.IssuingClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class IssuingServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: IssuingClient = mock()
    private val service = IssuingService(config, objectMapper, client)

    @Test
    fun displayIssuedCardDetailsToCustomer_passesPathBodyAndHeaders() {
        val cardToken = "ctok_123"
        val body = mapOf("customer" to "c_1")
        val stub = IssuingItemResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("shown" to true))
        whenever(client.displayIssuedCardDetailsToCustomer(any(), any(), any(), any(), any(), any(), any())).thenReturn(
            stub
        )

        val res = service.displayIssuedCardDetailsToCustomer(cardToken, body)
        assertEquals(stub, res)

        verify(client).displayIssuedCardDetailsToCustomer(
            cardToken = eq(cardToken),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listCards_withParams_passesQueryAndHeaders() {
        val contact = "ct_1"
        val pageNumber = 2
        val pageSize = 25
        val creationStartDate = 1700000000
        val creationEndDate = 1700100000
        val activationStartDate = 1700200000
        val activationEndDate = 1700300000
        val stub =
            IssuingListResponse(RapydStatus(null, "OK", null, null, null), data = listOf(mapOf("id" to "card_1")))
        whenever(
            client.listCards(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any())
        ).thenReturn(stub)

        val res = service.listCards(
            contact,
            pageNumber,
            pageSize,
            creationStartDate,
            creationEndDate,
            activationStartDate,
            activationEndDate
        )
        assertEquals(stub, res)

        verify(client).listCards(
            contact = eq(contact),
            pageNumber = eq(pageNumber),
            pageSize = eq(pageSize),
            creationStartDate = eq(creationStartDate),
            creationEndDate = eq(creationEndDate),
            activationStartDate = eq(activationStartDate),
            activationEndDate = eq(activationEndDate),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listCards_nullParams_stillSignsAndPassesNulls() {
        val stub = IssuingListResponse(RapydStatus(null, "OK", null, null, null), data = emptyList())
        whenever(
            client.listCards(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any())
        ).thenReturn(stub)

        service.listCards()

        verify(client).listCards(
            contact = isNull(),
            pageNumber = isNull(),
            pageSize = isNull(),
            creationStartDate = isNull(),
            creationEndDate = isNull(),
            activationStartDate = isNull(),
            activationEndDate = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createCard_passesBodyAndHeaders() {
        val body = mapOf("type" to "virtual", "contact" to "ct_1")
        val stub = IssuingItemResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("id" to "card_new"))
        whenever(client.createCard(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createCard(body)
        assertEquals(stub, res)

        verify(client).createCard(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveCard_passesHeaders() {
        val cardId = "card_123"
        val stub = IssuingItemResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("id" to cardId))
        whenever(client.retrieveCard(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveCard(cardId)
        assertEquals(stub, res)

        verify(client).retrieveCard(
            cardId = eq(cardId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun activateCard_passesBodyAndHeaders() {
        val body = mapOf("card_id" to "card_1")
        val stub = IssuingActionResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("activated" to true))
        whenever(client.activateCard(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.activateCard(body)
        assertEquals(stub, res)

        verify(client).activateCard(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun personalizeCard_passesBodyAndHeaders() {
        val body = mapOf("card_id" to "card_1", "name_on_card" to "JOHN DOE")
        val stub =
            IssuingActionResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("personalized" to true))
        whenever(client.personalizeCard(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.personalizeCard(body)
        assertEquals(stub, res)

        verify(client).personalizeCard(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun changeCardStatus_passesBodyAndHeaders() {
        val body = mapOf("card_id" to "card_1", "status" to "disable")
        val stub = IssuingActionResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("status" to "disable"))
        whenever(client.changeCardStatus(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.changeCardStatus(body)
        assertEquals(stub, res)

        verify(client).changeCardStatus(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listCardTransactions_withParams_passesQueryAndHeaders() {
        val cardId = "card_1"
        val endDate = "2025-01-01"
        val minAmount = "10"
        val maxAmount = "1000"
        val merchantNameSearch = "STORE"
        val merchantCategoryCode = "5411"
        val transactionStatus = "approved"
        val startDate = "2024-12-01"
        val limit = 50
        val stub = IssuingListResponse(RapydStatus(null, "OK", null, null, null), data = listOf(mapOf("tx" to 1)))
        whenever(
            client.listCardTransactions(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        ).thenReturn(stub)

        val res = service.listCardTransactions(
            cardId,
            endDate,
            minAmount,
            maxAmount,
            merchantNameSearch,
            merchantCategoryCode,
            transactionStatus,
            startDate,
            limit
        )
        assertEquals(stub, res)

        verify(client).listCardTransactions(
            cardId = eq(cardId),
            endDate = eq(endDate),
            minAmount = eq(minAmount),
            maxAmount = eq(maxAmount),
            merchantNameSearch = eq(merchantNameSearch),
            merchantCategoryCode = eq(merchantCategoryCode),
            transactionStatus = eq(transactionStatus),
            startDate = eq(startDate),
            limit = eq(limit),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listCardTransactions_nullParams_passesNullsAndHeaders() {
        val cardId = "card_2"
        val stub = IssuingListResponse(RapydStatus(null, "OK", null, null, null), data = emptyList())
        whenever(
            client.listCardTransactions(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        ).thenReturn(stub)

        service.listCardTransactions(cardId)

        verify(client).listCardTransactions(
            cardId = eq(cardId),
            endDate = isNull(),
            minAmount = isNull(),
            maxAmount = isNull(),
            merchantNameSearch = isNull(),
            merchantCategoryCode = isNull(),
            transactionStatus = isNull(),
            startDate = isNull(),
            limit = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveCardTransaction_passesPathAndHeaders() {
        val cardId = "card_1"
        val transactionId = "tx_1"
        val stub = IssuingItemResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("id" to transactionId))
        whenever(client.retrieveCardTransaction(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveCardTransaction(cardId, transactionId)
        assertEquals(stub, res)

        verify(client).retrieveCardTransaction(
            cardId = eq(cardId),
            transactionId = eq(transactionId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun setCardPin_passesBodyAndHeaders() {
        val body = mapOf("card_id" to "card_1", "pin" to "1234")
        val stub = IssuingActionResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("pin_set" to true))
        whenever(client.setCardPin(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.setCardPin(body)
        assertEquals(stub, res)

        verify(client).setCardPin(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createGooglePayCardToken_passesPathBodyAndHeaders() {
        val cardId = "card_1"
        val body = mapOf("googlepay" to true)
        val stub = IssuingItemResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("token" to "gpay_tok"))
        whenever(client.createGooglePayCardToken(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createGooglePayCardToken(cardId, body)
        assertEquals(stub, res)

        verify(client).createGooglePayCardToken(
            cardId = eq(cardId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun issueVirtualAccountNumber_passesBodyAndHeaders() {
        val body = mapOf("card_id" to "card_1")
        val stub = IssuingItemResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("iban" to "GB00..."))
        whenever(client.issueVirtualAccountNumber(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.issueVirtualAccountNumber(body)
        assertEquals(stub, res)

        verify(client).issueVirtualAccountNumber(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun simulateBlockCard_passesBodyAndHeaders() {
        val body = mapOf("card_id" to "card_1")
        val stub = IssuingActionResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("blocked" to true))
        whenever(client.simulateBlockCard(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.simulateBlockCard(body)
        assertEquals(stub, res)

        verify(client).simulateBlockCard(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun simulateCardAuthorizationEEA_passesBodyAndHeaders() {
        val body = mapOf("amount" to 10.0)
        val stub = IssuingItemResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("authorized" to true))
        whenever(client.simulateCardAuthorizationEEA(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.simulateCardAuthorizationEEA(body)
        assertEquals(stub, res)

        verify(client).simulateCardAuthorizationEEA(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun simulateCardReversalEEA_passesBodyAndHeaders() {
        val body = mapOf("transaction_id" to "tx_1")
        val stub = IssuingActionResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("reversed" to true))
        whenever(client.simulateCardReversalEEA(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.simulateCardReversalEEA(body)
        assertEquals(stub, res)

        verify(client).simulateCardReversalEEA(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun simulateClearingCardTransactionEEA_passesBodyAndHeaders() {
        val body = mapOf("transaction_id" to "tx_clr")
        val stub = IssuingActionResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("cleared" to true))
        whenever(client.simulateClearingCardTransactionEEA(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.simulateClearingCardTransactionEEA(body)
        assertEquals(stub, res)

        verify(client).simulateClearingCardTransactionEEA(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun simulateCardRefundEEA_passesBodyAndHeaders() {
        val body = mapOf("transaction_id" to "tx_ref", "amount" to 5.0)
        val stub = IssuingActionResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("refunded" to true))
        whenever(client.simulateCardRefundEEA(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.simulateCardRefundEEA(body)
        assertEquals(stub, res)

        verify(client).simulateCardRefundEEA(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun simulateCardAdjustmentEEA_passesBodyAndHeaders() {
        val body = mapOf("transaction_id" to "tx_adj", "amount" to 1.0)
        val stub = IssuingActionResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("adjusted" to true))
        whenever(client.simulateCardAdjustmentEEA(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.simulateCardAdjustmentEEA(body)
        assertEquals(stub, res)

        verify(client).simulateCardAdjustmentEEA(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
