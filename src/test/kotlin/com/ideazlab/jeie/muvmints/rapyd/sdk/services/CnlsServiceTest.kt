package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CnlsAddress
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CnlsQueriedMerchant
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.InitiateMerchantQueryRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.InitiateMerchantQueryResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RetrieveQueryResultsResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CnlsClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class CnlsServiceTest {
    private val config: RapydConfig = TestUtils.testConfig()
    private val objectMapper: ObjectMapper = TestUtils.objectMapper()
    private val client: CnlsClient = mock()
    private val service = CnlsService(config, objectMapper, client)

    @Test
    fun initiateMerchantQuery_passesBody_andSignedHeaders() {
        val body = InitiateMerchantQueryRequest(
            partnerMerchantReference = "pmr_1",
            partnerQueryReference = "pqr_1",
            searchCriteria = null,
            queriedMerchant = CnlsQueriedMerchant(
                businessCategory = "retail",
                dbaName = "ACME",
                email = null,
                phoneNumber = null,
                registrationId = null,
                registrationCountry = "US",
                address = CnlsAddress(
                    addressLine1 = "123 Main",
                    addressLine2 = "Suite 1",
                    city = "Metropolis",
                    country = "US",
                    postalCode = "10001"
                ),
                mcc = null,
                principals = null,
                isEcommerce = null,
                legalName = null,
                url = null
            ),
            referenceId = null
        )

        val stub = InitiateMerchantQueryResponse(
            RapydStatus(null, "OK", null, null, null),
            data = com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.InitiateMerchantQueryData(
                operationId = "op_1",
                partnerMerchantReference = body.partnerMerchantReference,
                partnerQueryReference = body.partnerQueryReference,
                status = "processing"
            )
        )

        whenever(client.initiateMerchantQuery(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.initiateMerchantQuery(body)
        assertEquals(stub, out)

        verify(client).initiateMerchantQuery(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveQueryResults_passesPath_andSignedHeaders() {
        val partnerQueryReference = "pqr_123"
        val stub = RetrieveQueryResultsResponse(
            RapydStatus(null, "OK", null, null, null),
            data = com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.RetrieveQueryResultsData(status = "completed")
        )

        whenever(client.retrieveQueryResults(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val out = service.retrieveQueryResults(partnerQueryReference)
        assertEquals(stub, out)

        verify(client).retrieveQueryResults(
            partnerQueryReference = eq(partnerQueryReference),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
