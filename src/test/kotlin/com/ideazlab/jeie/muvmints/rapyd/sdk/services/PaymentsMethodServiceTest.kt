package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListPaymentMethodsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.PaymentRequiredFieldsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.PaymentsMethodClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class PaymentsMethodServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: PaymentsMethodClient = mock()
    private val service = PaymentsMethodService(config, objectMapper, client)

    @Test
    fun listPaymentMethodsByCountry_withCurrency_lowercasesCountry_andPassesHeaders() {
        val country = "US" // mixed case on purpose
        val currency = "USD"

        val stub = ListPaymentMethodsResponse(
            RapydStatus(null, "OK", null, null, null),
            data = listOf(
                RapydPaymentMethod(
                    type = "us_ach",
                    name = "ACH",
                    category = "bank_transfer",
                    image = null,
                    country = country.lowercase(),
                    currency = currency,
                    isCancelable = true
                )
            )
        )

        whenever(client.listPaymentMethodsByCountry(any(), any(), any(), any(), any(), any(), any()))
            .thenReturn(stub)

        val res = service.listPaymentMethodsByCountry(country, currency)
        assertEquals(stub, res)

        verify(client).listPaymentMethodsByCountry(
            country = eq(country.lowercase()),
            currency = eq(currency),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listPaymentMethodsByCountry_withoutCurrency_passesNullCurrency_andHeaders() {
        val country = "GB"

        val stub = ListPaymentMethodsResponse(
            RapydStatus(null, "OK", null, null, null),
            data = emptyList()
        )
        whenever(client.listPaymentMethodsByCountry(any(), any(), any(), any(), any(), any(), any()))
            .thenReturn(stub)

        service.listPaymentMethodsByCountry(country)

        verify(client).listPaymentMethodsByCountry(
            country = eq(country.lowercase()),
            currency = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getRequiredFieldsForType_passesTypeAndSignedHeaders() {
        val type = "us_ach"
        val resp = PaymentRequiredFieldsResponse(
            RapydStatus(null, "OK", null, null, null),
            data = RapydPaymentRequiredFieldsData(
                type = type,
                fields = listOf(
                    RapydPaymentFieldDefinition(
                        name = "account_number",
                        type = "string",
                        regex = null,
                        description = null,
                        isRequired = true,
                        instructions = null
                    )
                ),
                paymentMethodOptions = emptyList<RapydPaymentOptionDefinition>(),
                paymentOptions = emptyList<RapydPaymentOptionDefinition>(),
                minimumExpirationSeconds = null,
                maximumExpirationSeconds = null
            )
        )
        whenever(client.getPaymentTypeRequiredFields(any(), any(), any(), any(), any(), any())).thenReturn(resp)

        val out = service.getRequiredFieldsForType(type)
        assertEquals(resp, out)

        verify(client).getPaymentTypeRequiredFields(
            type = eq(type),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
