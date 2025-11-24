package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import io.micronaut.serde.annotation.Serdeable

// Create Invoice Item request body based on components.schemas.v1_invoice_items_body
@Serdeable
data class CreateInvoiceItemRequest(
    val amount: Double? = null,
    val currency: String,
    val customer: String,
    val description: String? = null,
    val discountable: Boolean? = null,
    val invoice: String? = null,
    val metadata: Map<String, Any?>? = null,
    val quantity: Int? = null,
    val subscription: String? = null,
    val unit_amount: Double? = null
)

// Update Invoice Item request body based on components.schemas.invoice_items_invoiceItem_body
// Note: The upstream schema mainly includes invoice-related fields; we mirror the spec verbatim.
@Serdeable
data class UpdateInvoiceItemRequest(
    val days_until_due: Int? = null,
    val description: String? = null,
    val due_date: Long? = null,
    val metadata: Map<String, Any?>? = null,
    val payment_fields: Map<String, Any?>? = null,
    val statement_descriptor: String? = null,
    val tax_percent: Double? = null
)
