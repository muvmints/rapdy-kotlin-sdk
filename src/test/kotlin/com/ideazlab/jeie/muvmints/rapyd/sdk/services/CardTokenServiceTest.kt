package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CardFields
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateCardTokenRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CardTokenClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class CardTokenServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: CardTokenClient = mock()
    private val service = CardTokenService(config, objectMapper, client)

    @Test
    fun createCardTokenHostedPage_passesBodyAndSignedHeaders() {
        val body = CreateCardTokenRequest(
            billingAddressCollect = true,
            cancelUrl = "https://cancel",
            cardFields = CardFields(recurrenceType = "one_time"),
            completeUrl = "https://complete",
            completePaymentUrl = "https://complete-pay",
            country = "US",
            currency = "USD",
            customer = "c_123",
            errorPaymentUrl = "https://error",
            language = "en",
            pageExpiration = 3600,
            paymentMethodType = "card"
        )

        val stub = CardTokenHostedPageResponse(
            RapydStatus(null, "OK", null, null, null),
            data = listOf(
                RapydCardTokenHostedPage(
                    billingAddressCollect = body.billingAddressCollect,
                    cancelUrl = body.cancelUrl,
                    cardFields = RapydCardTokenCardFields(recurrenceType = body.cardFields?.recurrenceType),
                    category = "collect",
                    completeUrl = body.completeUrl,
                    completePaymentUrl = body.completePaymentUrl,
                    country = body.country,
                    currency = body.currency,
                    customer = body.customer,
                    errorCode = null,
                    errorPaymentUrl = body.errorPaymentUrl,
                    id = "cthp_1",
                    language = body.language,
                    merchantAlias = null,
                    merchantColor = null,
                    merchantCustomerSupport = RapydMerchantCustomerSupport(
                        email = null,
                        url = null,
                        phoneNumber = null,
                        merchantLogo = null
                    ),
                    merchantWebsite = null,
                    pageExpiration = body.pageExpiration,
                    paymentMethodType = body.paymentMethodType,
                    paymentParams = RapydCardTokenPaymentParams(
                        completePaymentUrl = body.completePaymentUrl,
                        errorPaymentUrl = body.errorPaymentUrl
                    ),
                    redirectUrl = null
                )
            )
        )

        whenever(client.createCardTokenHostedPage(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createCardTokenHostedPage(body)
        assertEquals(stub, res)

        verify(client).createCardTokenHostedPage(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
