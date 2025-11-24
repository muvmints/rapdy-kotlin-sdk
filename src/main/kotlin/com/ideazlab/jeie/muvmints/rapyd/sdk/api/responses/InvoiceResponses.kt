package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class InvoicesListResponse(
    val status: RapydStatus,
    val data: List<RapydInvoice>
)

@Response
data class InvoiceResponse(
    val status: RapydStatus,
    val data: RapydInvoice
)

@Response
data class InvoiceDeleteResponse(
    val status: RapydStatus,
    val data: RapydDeletedItem
)

@Response
data class InvoiceLinesResponse(
    val status: RapydStatus,
    // Structure of lines can vary; keep it flexible
    val data: Map<String, Any?>
)

@Response
data class UpcomingInvoiceLinesResponse(
    val status: RapydStatus,
    // Array of invoice items; keep it flexible to avoid overspecification
    val data: List<Map<String, Any?>>
)

@Serdeable
data class RapydInvoice(
    val id: String,
    val status: String?,
    val currency: String?,
    val customer: String?,
    val description: String?,
    @param:JsonProperty("attempt_count")
    val attemptCount: Int?,
    @param:JsonProperty("automatic_attempt_count")
    val automaticAttemptCount: Int?,
    @param:JsonProperty("billing")
    val billing: String?,
    @param:JsonProperty("billing_reason")
    val billingReason: String?,
    @param:JsonProperty("created_at")
    val createdAt: Long?,
    @param:JsonProperty("days_until_due")
    val daysUntilDue: Int?,
    @param:JsonProperty("due_date")
    val dueDate: Long?,
    @param:JsonProperty("hosted_invoice_url")
    val hostedInvoiceUrl: String?,
    @param:JsonProperty("invoice_pdf")
    val invoicePdf: String?,
    @param:JsonProperty("next_payment_attempt")
    val nextPaymentAttempt: Long?,
    @param:JsonProperty("number")
    val number: String?,
    @param:JsonProperty("paid")
    val paid: Boolean?,
    @param:JsonProperty("subtotal")
    val subtotal: Double?,
    @param:JsonProperty("tax")
    val tax: Double?,
    @param:JsonProperty("tax_percent")
    val taxPercent: Double?,
    @param:JsonProperty("total")
    val total: Double?,
    // Lines and nested objects can be complex; keep them as flexible structures
    val lines: Any?,
    val discount: Any?,
    val metadata: Map<String, Any?>?
)
