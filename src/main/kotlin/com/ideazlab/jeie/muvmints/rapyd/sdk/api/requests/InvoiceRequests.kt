package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateInvoiceRequest(
    val billing: String?,
    val currency: String?,
    val customer: String?,
    val days_until_due: Int? = null,
    val description: String? = null,
    val due_date: Long? = null,
    val metadata: Map<String, Any?>? = null,
    val payment_fields: Map<String, Any?>? = null,
    val payment_method: String? = null,
    val statement_descriptor: String? = null,
    val subscription: String? = null,
    val tax_percent: Double? = null
)

@Serdeable
data class UpdateInvoiceRequest(
    val days_until_due: Int? = null,
    val description: String? = null,
    val due_date: Long? = null,
    val metadata: Map<String, Any?>? = null,
    val statement_descriptor: String? = null,
    val tax_percent: Double? = null
)

@Serdeable
data class PayInvoiceRequest(
    val payment_method: String?
)
