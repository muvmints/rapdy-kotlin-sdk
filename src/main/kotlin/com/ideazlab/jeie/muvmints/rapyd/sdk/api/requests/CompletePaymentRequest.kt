package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

data class CompletePaymentRequest(
    val token: String,
    val param1: String? = null,
    val param2: String? = null
)
