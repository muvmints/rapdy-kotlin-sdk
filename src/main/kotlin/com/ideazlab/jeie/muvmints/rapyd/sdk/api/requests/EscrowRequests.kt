package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ReleaseEscrowRequest(
    val ewallets: EscrowWalletRelease
)

@Serdeable
data class EscrowWalletRelease(
    val ewallet: String,
    val amount: Double? = null,
    val percentage: Double? = null
)
