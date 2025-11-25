package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CardEligibilityRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CardEligibilityResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RapydCardEligibility
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RapydCardEligibilityAft
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RapydCardEligibilityOct
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CardEligibilityClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class CardEligibilityServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: CardEligibilityClient = mock()
    private val service = CardEligibilityService(config, objectMapper, client)

    @Test
    fun retrieveCardEligibility_passesBodyAndSignedHeaders() {
        val body = CardEligibilityRequest(cardNumber = "4111111111111111", transactionType = "ecom")
        val stub = CardEligibilityResponse(
            RapydStatus(null, "OK", null, null, null),
            data = RapydCardEligibility(
                aft = RapydCardEligibilityAft(domestic = "available", international = "available"),
                billingCurrency = "USD",
                cardType = "credit",
                issuerCountry = "US",
                oct = RapydCardEligibilityOct(
                    moneyTransferDomestic = "available",
                    moneyTransferInternational = "available",
                    nonMoneyTransferDomestic = "available",
                    nonMoneyTransferInternational = "available",
                    onlineGamblingDomestic = "unavailable",
                    onlineGamblingInternational = "unavailable"
                ),
                productType = "classic",
                scheme = "visa"
            )
        )
        whenever(client.cardEligibility(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveCardEligibility(body)
        assertEquals(stub, res)

        verify(client).cardEligibility(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
