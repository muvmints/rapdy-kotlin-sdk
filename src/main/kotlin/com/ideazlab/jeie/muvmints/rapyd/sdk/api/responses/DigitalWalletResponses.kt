package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class ApplePaySessionResponse(
    val status: RapydStatus,
    val data: ApplePaySessionData
)

@Serdeable
data class ApplePaySessionData(
    @param:JsonProperty("display_name")
    val displayName: String?,
    val domainName: String?,
    val epochTimestamp: Long?,
    val expiresAt: Long?,
    val merchantIdentifier: String?,
    val merchantSessionIdentifier: String?,
    val nonce: String?,
    val retries: Int?,
    val signature: String?
)
