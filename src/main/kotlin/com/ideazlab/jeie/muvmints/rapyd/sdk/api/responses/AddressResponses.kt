package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydAddress
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus

@Response
data class AddressResponse(
    val status: RapydStatus,
    val data: RapydAddress
)
