package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.RapydCustomerRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydCustomer
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydDiscount
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CustomerClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class CustomerServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: CustomerClient = mock()
    private val service = CustomerService(config, objectMapper, client)

    @Test
    fun listCustomers_passesQueryAndHeaders() {
        val startingAfter = "c_after"
        val endingBefore = "c_before"
        val limit = "25"
        val stub = CustomerListResponse(
            RapydStatus(null, "OK", null, null, null),
            emptyList()
        )
        whenever(client.listCustomers(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listCustomers(startingAfter, endingBefore, limit)
        assertEquals(stub, res)

        verify(client).listCustomers(
            startingAfter = eq(startingAfter),
            endingBefore = eq(endingBefore),
            limit = eq(limit),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listCustomers_withNulls_stillSignsAndPassesNulls() {
        val stub = CustomerListResponse(
            RapydStatus(null, "OK", null, null, null),
            emptyList()
        )
        whenever(client.listCustomers(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        service.listCustomers(null, null, null)

        verify(client).listCustomers(
            startingAfter = isNull(),
            endingBefore = isNull(),
            limit = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createCustomer_passesBodyAndHeaders() {
        val body = RapydCustomerRequest(name = "John Doe", email = "john@example.com")
        val stub = CustomerResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydCustomer(
                id = "c_1",
                delinquent = null,
                name = body.name,
                email = body.email,
                phoneNumber = null,
                businessVatId = null,
                metadata = emptyMap(),
                description = null,
                addresses = emptyList(),
                createdAt = null
            )
        )
        whenever(client.createCustomer(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createCustomer(body)
        assertEquals(stub, res)

        verify(client).createCustomer(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveCustomer_passesHeaders() {
        val customerId = "c_123"
        val stub = CustomerResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydCustomer(
                id = customerId,
                delinquent = null,
                name = null,
                email = null,
                phoneNumber = null,
                businessVatId = null,
                metadata = emptyMap(),
                description = null,
                addresses = emptyList(),
                createdAt = null
            )
        )
        whenever(client.retrieveCustomer(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveCustomer(customerId)
        assertEquals(stub, res)

        verify(client).retrieveCustomer(
            customerId = eq(customerId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updateCustomer_passesBodyAndHeaders() {
        val customerId = "c_456"
        val body = RapydCustomerRequest(name = "Jane Doe")
        val stub = CustomerResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydCustomer(
                id = customerId,
                delinquent = null,
                name = body.name,
                email = null,
                phoneNumber = null,
                businessVatId = null,
                metadata = emptyMap(),
                description = null,
                addresses = emptyList(),
                createdAt = null
            )
        )
        whenever(client.updateCustomer(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updateCustomer(customerId, body)
        assertEquals(stub, res)

        verify(client).updateCustomer(
            customerId = eq(customerId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deleteCustomer_passesHeaders() {
        val customerId = "c_789"
        val stub = CustomerResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydCustomer(
                id = customerId,
                delinquent = null,
                name = null,
                email = null,
                phoneNumber = null,
                businessVatId = null,
                metadata = emptyMap(),
                description = null,
                addresses = emptyList(),
                createdAt = null
            )
        )
        whenever(client.deleteCustomer(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deleteCustomer(customerId)
        assertEquals(stub, res)

        verify(client).deleteCustomer(
            customerId = eq(customerId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveDiscount_passesHeaders() {
        val discountId = "d_1234"
        val stub = DiscountResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydDiscount(
                id = null,
                amountOff = null,
                percentOff = null,
                currency = null,
                description = null,
                duration = null,
                durationInMonths = null,
                discountDurationInUses = null,
                discountValidUntil = null,
                discountValidityInMonths = null,
                valid = null,
                created = null,
                metadata = null,
                maxRedemptions = null,
                redeemBy = null,
                timesRedeemed = null
            )
        )
        whenever(client.retrieveDiscount(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveDiscount(discountId)
        assertEquals(stub, res)

        verify(client).retrieveDiscount(
            discountId = eq(discountId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deleteCustomerDiscount_passesHeaders() {
        val customerId = "c_999"
        val stub = CustomerDiscountDeleteResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydDeletedItem(deleted = true, id = customerId)
        )
        whenever(client.deleteCustomerDiscount(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deleteCustomerDiscount(customerId)
        assertEquals(stub, res)

        verify(client).deleteCustomerDiscount(
            customerId = eq(customerId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
