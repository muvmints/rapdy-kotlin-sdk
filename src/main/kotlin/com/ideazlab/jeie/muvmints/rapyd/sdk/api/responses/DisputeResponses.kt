package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydDispute
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus

@Response
data class DisputeResponse(
    val status: RapydStatus,
    val data: RapydDispute
)

@Response
data class ListDisputesResponse(
    val status: RapydStatus,
    val data: List<RapydDispute>
)
