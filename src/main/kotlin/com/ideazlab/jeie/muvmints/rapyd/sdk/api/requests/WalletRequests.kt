package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import io.micronaut.serde.annotation.Serdeable
import com.fasterxml.jackson.annotation.JsonProperty

@Serdeable
data class UpdateWalletRequest(
    @param:JsonProperty("ewallet_reference_id")
    val ewalletReferenceId: String? = null,
    @param:JsonProperty("first_name")
    val firstName: String? = null,
    @param:JsonProperty("last_name")
    val lastName: String? = null,
    val metadata: Map<String, Any?>? = null
)

@Serdeable
data class CreateContactRequest(
    @param:JsonProperty("address")
    val address: Any? = null, // use structured Address when needed
    @param:JsonProperty("business_details")
    val businessDetails: Any? = null,
    @param:JsonProperty("contact_type")
    val contactType: String,
    val country: String,
    @param:JsonProperty("date_of_birth")
    val dateOfBirth: String? = null,
    val email: String? = null,
    @param:JsonProperty("first_name")
    val firstName: String? = null,
    val gender: String? = null,
    @param:JsonProperty("house_type")
    val houseType: String? = null,
    @param:JsonProperty("identification_number")
    val identificationNumber: String? = null,
    @param:JsonProperty("identification_type")
    val identificationType: String? = null,
    @param:JsonProperty("last_name")
    val lastName: String? = null,
    @param:JsonProperty("marital_status")
    val maritalStatus: String? = null,
    val metadata: Map<String, Any?>? = null,
    @param:JsonProperty("middle_name")
    val middleName: String? = null,
    @param:JsonProperty("mothers_name")
    val mothersName: String? = null,
    val nationality: String? = null,
    @param:JsonProperty("phone_number")
    val phoneNumber: String? = null,
    @param:JsonProperty("second_last_name")
    val secondLastName: String? = null,
    @param:JsonProperty("send_notifications")
    val sendNotifications: Boolean? = null,
    @param:JsonProperty("contact_reference_id")
    val contactReferenceId: String? = null
)
