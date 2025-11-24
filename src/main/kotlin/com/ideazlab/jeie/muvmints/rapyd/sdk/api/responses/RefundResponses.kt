package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydRefund
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus

@Response
data class RefundResponse(
    val status: RapydStatus,
    val data: RapydRefund
)

@Response
data class ListRefundsResponse(
    val status: RapydStatus,
    val data: List<RapydRefund>
)
