package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateInvoiceRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.PayInvoiceRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateInvoiceRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.InvoiceDeleteResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.InvoiceLinesResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.InvoiceResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.InvoicesListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.UpcomingInvoiceLinesResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.InvoiceClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class InvoiceService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: InvoiceClient
) : BaseService() {

    fun listInvoices(params: Map<String, String?> = emptyMap()): InvoicesListResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/invoices")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listInvoices(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun createInvoice(body: CreateInvoiceRequest): InvoiceResponse {
        val path = "/v1/invoices"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createInvoice(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveInvoice(invoiceId: String): InvoiceResponse {
        val path = "/v1/invoices/$invoiceId"
        val signed = sign("get", path, null, config)
        return client.retrieveInvoice(
            invoiceId = invoiceId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updateInvoice(invoiceId: String, body: UpdateInvoiceRequest): InvoiceResponse {
        val path = "/v1/invoices/$invoiceId"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updateInvoice(
            invoiceId = invoiceId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun deleteInvoice(invoiceId: String): InvoiceDeleteResponse {
        val path = "/v1/invoices/$invoiceId"
        val signed = sign("delete", path, null, config)
        return client.deleteInvoice(
            invoiceId = invoiceId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun voidInvoice(invoiceId: String): InvoiceResponse {
        val path = "/v1/invoices/$invoiceId/void"
        val signed = sign("post", path, null, config)
        return client.voidInvoice(
            invoiceId = invoiceId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun finalizeInvoice(invoiceId: String): InvoiceResponse {
        val path = "/v1/invoices/$invoiceId/finalize"
        val signed = sign("post", path, null, config)
        return client.finalizeInvoice(
            invoiceId = invoiceId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun payInvoice(invoiceId: String, body: PayInvoiceRequest): InvoiceResponse {
        val path = "/v1/invoices/$invoiceId/pay"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.payInvoice(
            invoiceId = invoiceId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun markInvoiceUncollectible(invoiceId: String): InvoiceResponse {
        val path = "/v1/invoices/$invoiceId/mark_uncollectible"
        val signed = sign("post", path, null, config)
        return client.markInvoiceUncollectible(
            invoiceId = invoiceId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getInvoiceLines(invoiceId: String): InvoiceLinesResponse {
        val path = "/v1/invoices/$invoiceId/lines"
        val signed = sign("get", path, null, config)
        return client.getInvoiceLines(
            invoiceId = invoiceId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getUpcomingInvoice(params: Map<String, String?>): InvoiceResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/invoices/upcoming")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.getUpcomingInvoice(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getUpcomingInvoiceLines(params: Map<String, String?>): UpcomingInvoiceLinesResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/invoices/upcoming/lines")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.getUpcomingInvoiceLines(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
