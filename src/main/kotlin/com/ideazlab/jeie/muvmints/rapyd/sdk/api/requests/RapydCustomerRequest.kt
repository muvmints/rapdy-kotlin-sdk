package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.annotations.Request
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydCustomerAddress

@Request
data class RapydCustomerRequest(
    val name: String,
    val email: String? = "",
    @param:JsonProperty(value = "phone_number")
    val phoneNumber: String? = null,
    @param:JsonProperty(value = "business_vat_id")
    val businessVatId: String = "",
    val metadata: Map<String, Any?>? = null,
    val addresses: List<RapydCustomerAddress>? = null,
    val description: String? = null,
    val coupon: String? = null,
)