package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.RapydAddressRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.AddressResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydAddress
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.AddressClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class AddressServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: AddressClient = mock()
    private val service = AddressService(config, objectMapper, client)

    @Test
    fun createAddress_passesBody_andSignedHeaders() {
        val body = RapydAddressRequest(
            name = "John Doe",
            line1 = "123 Main St",
            line2 = null,
            city = "Metropolis",
            state = "NY",
            country = "US",
            zip = "10001",
            phoneNumber = "+1234567890"
        )

        val stub = AddressResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydAddress(
                id = "addr_1",
                name = body.name,
                line1 = body.line1,
                line2 = body.line2,
                line3 = body.line3,
                city = body.city,
                state = body.state,
                country = body.country,
                zip = body.zip,
                canton = null,
                district = null,
                phoneNumber = body.phoneNumber,
                createdAt = null,
                metadata = null
            )
        )
        whenever(client.createAddress(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createAddress(body)
        assertEquals(stub, res)

        verify(client).createAddress(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveAddress_passesPath_andSignedHeaders() {
        val addressId = "addr_123"
        val stub = AddressResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydAddress(
                id = addressId,
                name = "John",
                line1 = "L1",
                line2 = null,
                line3 = null,
                city = "C",
                state = "S",
                country = "US",
                zip = "00000",
                canton = null,
                district = null,
                phoneNumber = null,
                createdAt = null,
                metadata = null
            )
        )
        whenever(client.retrieveAddress(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveAddress(addressId)
        assertEquals(stub, res)

        verify(client).retrieveAddress(
            addressId = eq(addressId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updateAddress_passesPathBody_andSignedHeaders() {
        val addressId = "addr_789"
        val body = RapydAddressRequest(
            name = "Jane",
            line1 = "New L1",
            line2 = "New L2",
            city = "Gotham",
            state = "NJ",
            country = "US",
            zip = "07001",
            phoneNumber = null
        )
        val stub = AddressResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydAddress(
                id = addressId,
                name = body.name,
                line1 = body.line1,
                line2 = body.line2,
                line3 = body.line3,
                city = body.city,
                state = body.state,
                country = body.country,
                zip = body.zip,
                canton = null,
                district = null,
                phoneNumber = body.phoneNumber,
                createdAt = null,
                metadata = null
            )
        )
        whenever(client.updateAddress(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updateAddress(addressId, body)
        assertEquals(stub, res)

        verify(client).updateAddress(
            addressId = eq(addressId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deleteAddress_passesPath_andSignedHeaders() {
        val addressId = "addr_del"
        val stub = AddressResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydAddress(
                id = addressId,
                name = null,
                line1 = null,
                line2 = null,
                line3 = null,
                city = null,
                state = null,
                country = null,
                zip = null,
                canton = null,
                district = null,
                phoneNumber = null,
                createdAt = null,
                metadata = null
            )
        )
        whenever(client.deleteAddress(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deleteAddress(addressId)
        assertEquals(stub, res)

        verify(client).deleteAddress(
            addressId = eq(addressId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
