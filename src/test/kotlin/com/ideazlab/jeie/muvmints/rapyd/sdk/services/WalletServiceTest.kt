package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateContactRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateWalletRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.WalletClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class WalletServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: WalletClient = mock()
    private val service = WalletService(config, objectMapper, client)

    @Test
    fun listWallets_withAllParams_passesQueryParamsAndHeaders() {
        val phoneNumber = "+1234567890"
        val email = "user@example.com"
        val ewalletReferenceId = "ref_123"
        val pageNumber = 2
        val pageSize = 25
        val type = "company"
        val minBalance = 100
        val currency = "USD"

        val stub = WalletListResponse(
            RapydStatus(null, "OK", null, null, null),
            data = listOf(
                RapydWallet(
                    id = "ew_1",
                    status = "active",
                    type = type,
                    ewalletReferenceId = ewalletReferenceId,
                    contacts = listOf(
                        RapydWalletContact(
                            id = "wc_1",
                            firstName = "John",
                            lastName = "Doe",
                            email = email,
                            phoneNumber = phoneNumber,
                            contactType = "personal",
                            country = "US"
                        )
                    )
                )
            )
        )
        whenever(
            client.listWallets(
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()
            )
        ).thenReturn(stub)

        val res = service.listWallets(
            phoneNumber, email, ewalletReferenceId, pageNumber, pageSize, type, minBalance, currency
        )
        assertEquals(stub, res)

        verify(client).listWallets(
            phoneNumber = eq(phoneNumber),
            email = eq(email),
            ewalletReferenceId = eq(ewalletReferenceId),
            pageNumber = eq(pageNumber),
            pageSize = eq(pageSize),
            type = eq(type),
            minBalance = eq(minBalance),
            currency = eq(currency),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listWallets_withNullParams_stillSignsAndPassesNulls() {
        val stub = WalletListResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(
            client.listWallets(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        ).thenReturn(stub)

        service.listWallets()

        verify(client).listWallets(
            phoneNumber = isNull(),
            email = isNull(),
            ewalletReferenceId = isNull(),
            pageNumber = isNull(),
            pageSize = isNull(),
            type = isNull(),
            minBalance = isNull(),
            currency = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createWallet_passesBodyAndHeaders() {
        val contactReq = RapydWalletContactRequest(
            firsNtame = "Jane",
            lastName = "Smith",
            email = "jane@example.com",
            phoneNumber = "+1112223333",
            contactType = "personal",
            country = "GB"
        )
        val body = RapydCreateWalletRequest(
            type = "person",
            ewalletReferenceId = "ref_999",
            contact = contactReq
        )
        val stub = CreateWalletResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydWallet(
                id = "ew_new",
                status = "active",
                type = body.type,
                ewalletReferenceId = body.ewalletReferenceId,
                contacts = emptyList()
            )
        )
        whenever(client.createWallet(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createWallet(body)
        assertEquals(stub, res)

        verify(client).createWallet(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getWalletAccounts_passesHeaders() {
        val ewalletId = "ew_123"
        val stub = WalletAccountsResponse(
            RapydStatus(null, "OK", null, null, null),
            data = listOf(
                RapydWalletAccount(id = "acc_1", currency = "USD", balance = 10.5)
            )
        )
        whenever(client.getWalletAccounts(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getWalletAccounts(ewalletId)
        assertEquals(stub, res)

        verify(client).getWalletAccounts(
            ewalletId = eq(ewalletId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveWallet_passesHeaders() {
        val ewalletToken = "ew_tok_1"
        val stub = CreateWalletResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydWallet(
                id = ewalletToken,
                status = "active",
                type = "person",
                ewalletReferenceId = null,
                contacts = emptyList()
            )
        )
        whenever(client.retrieveWallet(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveWallet(ewalletToken)
        assertEquals(stub, res)

        verify(client).retrieveWallet(
            ewalletToken = eq(ewalletToken),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updateWallet_passesBodyAndHeaders() {
        val ewalletToken = "ew_tok_2"
        val body = UpdateWalletRequest(ewalletReferenceId = "ref_update", firstName = "Alex", lastName = "M")
        val stub = CreateWalletResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydWallet(
                id = ewalletToken,
                status = "updated",
                type = "person",
                ewalletReferenceId = body.ewalletReferenceId,
                contacts = emptyList()
            )
        )
        whenever(client.updateWallet(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updateWallet(ewalletToken, body)
        assertEquals(stub, res)

        verify(client).updateWallet(
            ewalletToken = eq(ewalletToken),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deleteWallet_passesHeaders() {
        val ewalletToken = "ew_tok_3"
        val stub = WalletActionResponse(RapydStatus(null, "OK", null, null, null))
        whenever(client.deleteWallet(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deleteWallet(ewalletToken)
        assertEquals(stub, res)

        verify(client).deleteWallet(
            ewalletToken = eq(ewalletToken),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun changeWalletStatus_passesPathParamsAndHeaders() {
        val ewalletToken = "ew_tok_4"
        val status = "disable"
        val stub = WalletActionResponse(RapydStatus(null, "OK", null, null, null))
        whenever(client.changeWalletStatus(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.changeWalletStatus(ewalletToken, status)
        assertEquals(stub, res)

        verify(client).changeWalletStatus(
            ewalletToken = eq(ewalletToken),
            status = eq(status),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listContacts_passesHeadersAndPath() {
        val ewalletId = "ew_555"
        val stub = ContactListResponse(
            RapydStatus(null, "OK", null, null, null),
            data = listOf(
                RapydContact(
                    id = "ct_1",
                    ewallet = ewalletId,
                    address = null,
                    businessDetails = null,
                    complianceProfile = null,
                    contactReferenceId = null,
                    contactType = "personal",
                    country = "US",
                    createdAt = null,
                    dateOfBirth = null,
                    email = "c@example.com",
                    firstName = "C",
                    gender = null,
                    houseType = null,
                    identificationNumber = null,
                    identificationType = null,
                    lastName = "Test",
                    maritalStatus = null,
                    metadata = null,
                    middleName = null,
                    mothersName = null,
                    nationality = null,
                    phoneNumber = null,
                    secondLastName = null,
                    sendNotifications = null
                )
            )
        )
        whenever(client.listContacts(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listContacts(ewalletId)
        assertEquals(stub, res)

        verify(client).listContacts(
            ewalletId = eq(ewalletId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createContact_passesBodyAndHeaders() {
        val ewalletId = "ew_777"
        val body = CreateContactRequest(
            contactType = "personal",
            country = "US",
            email = "x@y.com",
            firstName = "X",
            lastName = "Y"
        )
        val stub = ContactResponse(
            RapydStatus(null, "OK", null, null, null),
            data = RapydContact(
                id = "ct_2",
                ewallet = ewalletId,
                address = null,
                businessDetails = null,
                complianceProfile = null,
                contactReferenceId = null,
                contactType = body.contactType,
                country = body.country,
                createdAt = null,
                dateOfBirth = body.dateOfBirth,
                email = body.email,
                firstName = body.firstName,
                gender = body.gender,
                houseType = body.houseType,
                identificationNumber = body.identificationNumber,
                identificationType = body.identificationType,
                lastName = body.lastName,
                maritalStatus = body.maritalStatus,
                metadata = body.metadata,
                middleName = body.middleName,
                mothersName = body.mothersName,
                nationality = body.nationality,
                phoneNumber = body.phoneNumber,
                secondLastName = body.secondLastName,
                sendNotifications = body.sendNotifications
            )
        )
        whenever(client.createContact(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createContact(ewalletId, body)
        assertEquals(stub, res)

        verify(client).createContact(
            ewalletId = eq(ewalletId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getContact_passesHeaders() {
        val ewalletId = "ew_888"
        val contactId = "ct_888"
        val stub = ContactResponse(
            RapydStatus(null, "OK", null, null, null),
            data = RapydContact(
                id = contactId,
                ewallet = ewalletId,
                address = null,
                businessDetails = null,
                complianceProfile = null,
                contactReferenceId = null,
                contactType = null,
                country = null,
                createdAt = null,
                dateOfBirth = null,
                email = null,
                firstName = null,
                gender = null,
                houseType = null,
                identificationNumber = null,
                identificationType = null,
                lastName = null,
                maritalStatus = null,
                metadata = null,
                middleName = null,
                mothersName = null,
                nationality = null,
                phoneNumber = null,
                secondLastName = null,
                sendNotifications = null
            )
        )
        whenever(client.getContact(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getContact(ewalletId, contactId)
        assertEquals(stub, res)

        verify(client).getContact(
            ewalletId = eq(ewalletId),
            contactId = eq(contactId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updateContact_passesBodyAndHeaders() {
        val ewalletId = "ew_999"
        val contactId = "ct_999"
        val body = mapOf("k" to "v", "int" to 1)
        val stub = ContactResponse(
            RapydStatus(null, "OK", null, null, null),
            data = RapydContact(
                id = contactId,
                ewallet = ewalletId,
                address = null,
                businessDetails = null,
                complianceProfile = null,
                contactReferenceId = null,
                contactType = null,
                country = null,
                createdAt = null,
                dateOfBirth = null,
                email = null,
                firstName = null,
                gender = null,
                houseType = null,
                identificationNumber = null,
                identificationType = null,
                lastName = null,
                maritalStatus = null,
                metadata = body,
                middleName = null,
                mothersName = null,
                nationality = null,
                phoneNumber = null,
                secondLastName = null,
                sendNotifications = null
            )
        )
        whenever(client.updateContact(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updateContact(ewalletId, contactId, body)
        assertEquals(stub, res)

        verify(client).updateContact(
            ewalletId = eq(ewalletId),
            contactId = eq(contactId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deleteContact_passesHeaders() {
        val ewalletId = "ew_del"
        val contactId = "ct_del"
        val stub = DeleteContactResponse(
            RapydStatus(null, "OK", null, null, null),
            data = DeleteContactData(delete = true, id = contactId)
        )
        whenever(client.deleteContact(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deleteContact(ewalletId, contactId)
        assertEquals(stub, res)

        verify(client).deleteContact(
            ewalletId = eq(ewalletId),
            contactId = eq(contactId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getContactComplianceLevels_passesHeaders() {
        val ewalletId = "ew_cmp"
        val contactId = "ct_cmp"
        val stub = ComplianceLevelsResponse(
            RapydStatus(null, "OK", null, null, null),
            data = ComplianceLevelsData(
                complianceLevels = listOf(
                    ComplianceLevel(
                        level = 1,
                        elements = listOf(ComplianceElement(elementName = "email", verified = true))
                    )
                )
            )
        )
        whenever(client.getContactComplianceLevels(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getContactComplianceLevels(ewalletId, contactId)
        assertEquals(stub, res)

        verify(client).getContactComplianceLevels(
            ewalletId = eq(ewalletId),
            contactId = eq(contactId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
