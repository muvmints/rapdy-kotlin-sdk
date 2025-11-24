package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class InvoiceItemListResponse(
    val status: RapydStatus,
    val data: List<RapydInvoiceItem>
)

@Response
data class InvoiceItemDeleteResponse(
    val status: RapydStatus,
    val data: RapydDeletedItem
)

@Serdeable
data class RapydInvoiceItem(
    val id: String,
    val amount: Double?,
    val currency: String?,
    val customer: String?,
    val date: String?,
    val description: String?,
    val discountable: Boolean?,
    @param:JsonProperty("invoice_item")
    val invoiceItem: String?,
    val invoice: String?,
    val metadata: Map<String, Any?>?,
    val period: RapydInvoiceItemPeriod?,
    val plan: Any?,
    val proration: Boolean?,
    val quantity: Int?,
    val subscription: String?,
    @param:JsonProperty("unit_amount")
    val unitAmount: Double?
)

@Serdeable
data class RapydInvoiceItemPeriod(
    val start: String?,
    val end: String?
)
