package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class RapydAddressRequest(
    val name: String?,
    @param:JsonProperty("line_1")
    val line1: String?,
    @param:JsonProperty("line_2")
    val line2: String? = null,
    @param:JsonProperty("line_3")
    val line3: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val zip: String? = null,
    val canton: String? = null,
    val district: String? = null,
    @param:JsonProperty("phone_number")
    val phoneNumber: String? = null,
    val metadata: Map<String, Any?>? = null
)
