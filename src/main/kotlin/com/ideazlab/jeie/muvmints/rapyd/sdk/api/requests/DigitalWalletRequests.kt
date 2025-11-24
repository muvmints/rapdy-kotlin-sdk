package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ApplePaySessionRequest(
    @param:JsonProperty("display_name")
    val displayName: String,
    @param:JsonProperty("initiative_context")
    val initiativeContext: String
)
