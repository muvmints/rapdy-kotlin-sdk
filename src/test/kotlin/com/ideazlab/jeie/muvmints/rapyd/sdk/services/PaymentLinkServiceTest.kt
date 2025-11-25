package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreatePaymentLinkRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PaymentLinkResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RapydPaymentLink
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.PaymentLinkClient
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class PaymentLinkServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: PaymentLinkClient = mock()
    private val service = PaymentLinkService(config, objectMapper, client)

    @Test
    fun createPaymentLink_passesBodyAndSignedHeaders() {
        val body = CreatePaymentLinkRequest(
            amount = "10.00",
            amountIsEditable = false,
            checkout = mapOf("key" to "val"),
            country = "US",
            currency = "USD",
            customer = "c_123",
            fixedSide = "buy",
            language = "en",
            maxPayments = 1,
            merchantReferenceId = "mr_1",
            items = listOf(mapOf("name" to "Test", "amount" to 10.0)),
            requestedCurrency = null
        )

        val stub = PaymentLinkResponse(
            RapydStatus(null, "OK", null, null, null),
            data = listOf(
                RapydPaymentLink(
                    id = "pl_1",
                    amount = 10.0,
                    amountIsEditable = body.amountIsEditable,
                    country = body.country,
                    currency = body.currency,
                    customer = body.customer,
                    fixedSide = body.fixedSide,
                    language = body.language,
                    maxPayments = body.maxPayments,
                    merchantAlias = null,
                    merchantColor = null,
                    merchantCustomerSupport = null,
                    merchantLogo = null,
                    merchantPrivacyPolicy = null,
                    merchantReferenceId = body.merchantReferenceId,
                    merchantWebsite = null,
                    pageExpiration = null,
                    requestedCurrency = body.requestedCurrency,
                    redirectUrl = null,
                    status = "created",
                    template = emptyMap()
                )
            )
        )

        whenever(client.createPaymentLink(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createPaymentLink(body)
        Assertions.assertEquals(stub, res)

        verify(client).createPaymentLink(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrievePaymentLink_passesPathAndSignedHeaders() {
        val paymentLinkId = "pl_123"
        val stub = PaymentLinkResponse(
            RapydStatus(null, "OK", null, null, null),
            data = listOf(
                RapydPaymentLink(
                    id = paymentLinkId,
                    amount = null,
                    amountIsEditable = null,
                    country = null,
                    currency = null,
                    customer = null,
                    fixedSide = null,
                    language = null,
                    maxPayments = null,
                    merchantAlias = null,
                    merchantColor = null,
                    merchantCustomerSupport = null,
                    merchantLogo = null,
                    merchantPrivacyPolicy = null,
                    merchantReferenceId = null,
                    merchantWebsite = null,
                    pageExpiration = null,
                    requestedCurrency = null,
                    redirectUrl = null,
                    status = null,
                    template = null
                )
            )
        )
        whenever(client.retrievePaymentLink(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrievePaymentLink(paymentLinkId)
        Assertions.assertEquals(stub, res)

        verify(client).retrievePaymentLink(
            paymentLink = eq(paymentLinkId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}