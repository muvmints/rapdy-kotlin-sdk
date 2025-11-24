package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class EscrowResponseWrapper(
    val status: RapydStatus,
    val data: EscrowResponse
)

// Some Escrow endpoints return only the data wrapper without status (as per OpenAPI inline_response_200_30)
@Serdeable
data class EscrowResponseDataOnly(
    val data: EscrowResponse
)

@Serdeable
data class EscrowResponse(
    @JsonProperty("amount_on_hold")
    val amountOnHold: Double?,
    @JsonProperty("created_at")
    val createdAt: Long?,
    val currency: String?,
    @JsonProperty("escrow_releases")
    val escrowReleases: EscrowReleases?,
    val id: String?,
    @JsonProperty("last_payment_completion")
    val lastPaymentCompletion: Long?,
    val payment: String?,
    val status: String?,
    @JsonProperty("total_amount_released")
    val totalAmountReleased: Double?,
    @JsonProperty("updated_at")
    val updatedAt: Long?
)

@Serdeable
data class EscrowReleases(
    val data: List<EscrowRelease>?,
    @JsonProperty("has_more")
    val hasMore: Boolean?,
    @JsonProperty("total_count")
    val totalCount: Int?,
    val url: String?
)

@Serdeable
data class EscrowRelease(
    val amount: Double?,
    @JsonProperty("created_at")
    val createdAt: Long?,
    val currency: String?,
    val ewallets: EscrowWalletRelease?,
    val id: String?,
    @JsonProperty("proportional_release")
    val proportionalRelease: Boolean?,
    val trigger: String?
)

@Serdeable
data class EscrowWalletRelease(
    val ewallet: String?,
    val amount: Double?,
    val percentage: Double?
)
