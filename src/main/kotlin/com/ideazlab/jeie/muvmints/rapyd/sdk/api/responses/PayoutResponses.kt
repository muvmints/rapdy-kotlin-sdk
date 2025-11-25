package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus

@Response
data class PayoutResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)

@Response
data class ListPayoutsResponse(
    val status: RapydStatus,
    val data: List<Map<String, Any?>>
)

@Response
data class MassPayoutCreateResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)
