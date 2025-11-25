package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateCheckoutPageRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CheckoutPageResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RapydCheckoutPage
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CheckoutClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class CheckoutServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: CheckoutClient = mock()
    private val service = CheckoutService(config, objectMapper, client)

    @Test
    fun createCheckoutPage_passesBody_andSignedHeaders() {
        val body = CreateCheckoutPageRequest(
            amount = 12.34,
            currency = "USD",
            description = "Test order",
            customer = "cus_123",
            paymentMethodTypesInclude = listOf("us_visa"),
            completeUrl = "https://merchant/complete",
            cancelUrl = "https://merchant/cancel"
        )

        val stub = CheckoutPageResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydCheckoutPage(
                id = "checkout_1",
                status = "NEW",
                redirectUrl = "https://redirect",
                completeCheckoutUrl = body.completeUrl,
                completePaymentUrl = null,
                cancelCheckoutUrl = body.cancelUrl,
                errorPaymentUrl = null,
                merchantWebsite = "https://merchant",
                pageExpiration = 123456789L,
                payment = emptyList()
            )
        )
        whenever(client.createCheckoutPage(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.createCheckoutPage(body)
        assertEquals(stub, out)

        verify(client).createCheckoutPage(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveCheckoutPage_passesPath_andSignedHeaders() {
        val token = "checkout_token_abc"
        val stub = CheckoutPageResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydCheckoutPage(
                id = token,
                status = "NEW",
                redirectUrl = null,
                completeCheckoutUrl = null,
                completePaymentUrl = null,
                cancelCheckoutUrl = null,
                errorPaymentUrl = null,
                merchantWebsite = null,
                pageExpiration = null,
                payment = null
            )
        )
        whenever(client.retrieveCheckoutPage(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.retrieveCheckoutPage(token)
        assertEquals(stub, out)

        verify(client).retrieveCheckoutPage(
            checkoutToken = eq(token),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
