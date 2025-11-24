package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydGroupPayment
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus

@Response
data class GroupPaymentResponse(
    val status: RapydStatus,
    val data: RapydGroupPayment
)

@Response
data class GroupPaymentRefundResponse(
    val status: RapydStatus,
    val data: RapydGroupPayment
)
