package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus

@Response
data class IssuingListResponse(
    val status: RapydStatus,
    val data: List<Map<String, Any?>>
)

@Response
data class IssuingItemResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)

@Response
data class IssuingActionResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)
