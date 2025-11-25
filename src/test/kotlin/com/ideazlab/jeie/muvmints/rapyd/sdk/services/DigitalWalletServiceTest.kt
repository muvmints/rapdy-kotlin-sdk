package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.ApplePaySessionRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ApplePaySessionData
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ApplePaySessionResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.DigitalWalletClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class DigitalWalletServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: DigitalWalletClient = mock()
    private val service = DigitalWalletService(config, objectMapper, client)

    @Test
    fun retrieveApplePaySession_withBody_passesBodyAndSignedHeaders() {
        val body = ApplePaySessionRequest(displayName = "Merchant X", initiativeContext = "example.com")
        val stub = ApplePaySessionResponse(
            RapydStatus(null, "OK", null, null, null),
            ApplePaySessionData(
                displayName = body.displayName,
                domainName = body.initiativeContext,
                epochTimestamp = 1700000000,
                expiresAt = 1700003600,
                merchantIdentifier = "mid_1",
                merchantSessionIdentifier = "ms_1",
                nonce = "nonce",
                retries = 0,
                signature = "sig"
            )
        )
        whenever(client.getApplePaySession(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveApplePaySession(body)
        assertEquals(stub, res)

        verify(client).getApplePaySession(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveApplePaySession_withArgs_wrapsIntoRequest_andPassesSignedHeaders() {
        val displayName = "Merchant Y"
        val initiativeContext = "shop.example"
        val expectedBody = ApplePaySessionRequest(displayName = displayName, initiativeContext = initiativeContext)
        val stub = ApplePaySessionResponse(
            RapydStatus(null, "OK", null, null, null),
            ApplePaySessionData(
                displayName = displayName,
                domainName = initiativeContext,
                epochTimestamp = null,
                expiresAt = null,
                merchantIdentifier = null,
                merchantSessionIdentifier = null,
                nonce = null,
                retries = null,
                signature = null
            )
        )
        whenever(client.getApplePaySession(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveApplePaySession(displayName, initiativeContext)
        assertEquals(stub, res)

        verify(client).getApplePaySession(
            body = eq(expectedBody),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
