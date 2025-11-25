package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateVirtualAccountRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.SimulateBankTransferToVirtualAccountRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateRequestedCurrencyRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.VirtualAccountClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class VirtualAccountServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: VirtualAccountClient = mock()
    private val service = VirtualAccountService(config, objectMapper, client)

    @Test
    fun createVirtualAccount_passesBodyAndSignedHeaders() {
        val body = CreateVirtualAccountRequest(
            country = "US",
            currency = "USD",
            ewallet = "ew_1",
            description = "desc",
            merchantReferenceId = "mr_1",
            requestedCurrency = "USD",
            metadata = mapOf("k" to "v")
        )
        val stub = CreateVirtualAccountResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("id" to "va_1", "status" to "created")
        )
        whenever(client.createVirtualAccount(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createVirtualAccount(body)
        assertEquals(stub, res)

        verify(client).createVirtualAccount(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun simulateBankTransferToVirtualAccount_passesBodyAndSignedHeaders() {
        val body = SimulateBankTransferToVirtualAccountRequest(
            amount = "100.00",
            currency = "USD",
            issuedBankAccount = "iba_1"
        )
        val stub = SimulateBankTransferToVirtualAccountResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("simulated" to true)
        )
        whenever(client.simulateBankTransfer(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.simulateBankTransferToVirtualAccount(body)
        assertEquals(stub, res)

        verify(client).simulateBankTransfer(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveVirtualAccount_passesPathAndSignedHeaders() {
        val virtualAccountId = "va_123"
        val stub =
            VirtualAccountResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("id" to virtualAccountId))
        whenever(client.retrieveVirtualAccount(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveVirtualAccount(virtualAccountId)
        assertEquals(stub, res)

        verify(client).retrieveVirtualAccount(
            virtualAccountId = eq(virtualAccountId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updateRequestedCurrency_passesPathBodyAndSignedHeaders() {
        val virtualAccountId = "va_upd_1"
        val body = UpdateRequestedCurrencyRequest(requestingCurrency = "EUR")
        val stub = UpdateRequestedCurrencyResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("requested_currency" to "EUR")
        )
        whenever(client.updateRequestedCurrency(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updateRequestedCurrency(virtualAccountId, body)
        assertEquals(stub, res)

        verify(client).updateRequestedCurrency(
            virtualAccountId = eq(virtualAccountId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun closeVirtualAccount_passesPathAndSignedHeaders() {
        val virtualAccountId = "va_close_1"
        val stub =
            CloseVirtualAccountResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("closed" to true))
        whenever(client.closeVirtualAccount(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.closeVirtualAccount(virtualAccountId)
        assertEquals(stub, res)

        verify(client).closeVirtualAccount(
            virtualAccountId = eq(virtualAccountId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun capabilitiesOfVirtualAccounts_passesPathAndSignedHeaders() {
        val country = "US"
        val stub = CapabilitiesOfVirtualAccountsResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("country" to country)
        )
        whenever(client.capabilitiesOfVirtualAccounts(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.capabilitiesOfVirtualAccounts(country)
        assertEquals(stub, res)

        verify(client).capabilitiesOfVirtualAccounts(
            country = eq(country),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getRemitterDetails_passesBothPathParamsAndSignedHeaders() {
        val virtualAccountId = "va_rem_1"
        val transactionId = "tr_1"
        val stub = RemitterDetailsResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("transaction_id" to transactionId)
        )
        whenever(client.getRemitterDetails(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getRemitterDetails(virtualAccountId, transactionId)
        assertEquals(stub, res)

        verify(client).getRemitterDetails(
            virtualAccountId = eq(virtualAccountId),
            transactionId = eq(transactionId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getTransactionDetails_passesBothPathParamsAndSignedHeaders() {
        val virtualAccountId = "va_tx_1"
        val transactionId = "tx_1"
        val stub = TransactionDetailsResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("transactionId" to transactionId)
        )
        whenever(client.getTransactionDetails(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getTransactionDetails(virtualAccountId, transactionId)
        assertEquals(stub, res)

        verify(client).getTransactionDetails(
            virtualAccountId = eq(virtualAccountId),
            transactionId = eq(transactionId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
