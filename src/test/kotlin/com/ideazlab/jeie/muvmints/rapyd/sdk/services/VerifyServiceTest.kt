package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateHostedVerifyApplicationRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.VerifyIdentityRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.VerifyClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class VerifyServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: VerifyClient = mock()
    private val service = VerifyService(config, objectMapper, client)

    @Test
    fun verifyIdentity_passesBodyAndHeaders() {
        val body = VerifyIdentityRequest(
            ewallet = "ew_1",
            contact = "ct_1",
            country = "US",
            id_type = "passport",
            id_number = "12345",
            first_name = "John",
            last_name = "Doe",
            date_of_birth = "1990-01-01",
            additional_fields = mapOf("k" to "v")
        )
        val stub = VerifyIdentityResponse(RapydStatus(null, "OK", null, null, null), data = mapOf("verified" to true))
        whenever(client.verifyIdentity(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.verifyIdentity(body)
        assertEquals(stub, res)

        verify(client).verifyIdentity(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listIdDocuments_withCountry_passesQueryAndHeaders() {
        val country = "GB"
        val stub = VerifyIdDocumentsResponse(RapydStatus(null, "OK", null, null, null), data = emptyList())
        whenever(client.listIdDocuments(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listIdDocuments(country)
        assertEquals(stub, res)

        verify(client).listIdDocuments(
            country = eq(country),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listIdDocuments_withoutCountry_passesNullAndHeaders() {
        val stub = VerifyIdDocumentsResponse(RapydStatus(null, "OK", null, null, null), data = emptyList())
        whenever(client.listIdDocuments(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        service.listIdDocuments(null)

        verify(client).listIdDocuments(
            country = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getApplicationTypesByCountry_passesPathAndHeaders() {
        val country = "US"
        val stub =
            VerifyApplicationTypesByCountryResponse(RapydStatus(null, "OK", null, null, null), data = emptyList())
        whenever(client.getApplicationTypesByCountry(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getApplicationTypesByCountry(country)
        assertEquals(stub, res)

        verify(client).getApplicationTypesByCountry(
            country = eq(country),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getApplicationStatus_passesPathAndHeaders() {
        val applicationId = "va_app_1"
        val stub = VerifyApplicationStatusResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("id" to applicationId)
        )
        whenever(client.getApplicationStatus(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getApplicationStatus(applicationId)
        assertEquals(stub, res)

        verify(client).getApplicationStatus(
            applicationId = eq(applicationId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createHostedApplication_passesBodyAndHeaders() {
        val body = CreateHostedVerifyApplicationRequest(
            country = "US",
            entity_type = "company",
            reference_id = "ref_1",
            success_url = "https://ok",
            cancel_url = "https://cancel",
            merchant_color = "#000000",
            page_expiration = 3600,
            metadata = mapOf("k" to "v"),
            applicant_details = mapOf("name" to "Acme"),
            prefill_details = mapOf("country" to "US")
        )
        val stub = VerifyHostedApplicationResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("id" to "vh_1", "status" to "created")
        )
        whenever(client.createHostedApplication(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createHostedApplication(body)
        assertEquals(stub, res)

        verify(client).createHostedApplication(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getHostedApplication_passesPathAndHeaders() {
        val verifyAppId = "vh_123"
        val stub = VerifyHostedApplicationResponse(
            RapydStatus(null, "OK", null, null, null),
            data = mapOf("id" to verifyAppId)
        )
        whenever(client.getHostedApplication(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getHostedApplication(verifyAppId)
        assertEquals(stub, res)

        verify(client).getHostedApplication(
            verifyAppId = eq(verifyAppId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
