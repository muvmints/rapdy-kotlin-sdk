package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateInvoiceRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.PayInvoiceRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateInvoiceRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.InvoiceDeleteResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.InvoiceLinesResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.InvoiceResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.InvoicesListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.UpcomingInvoiceLinesResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface InvoiceClient {

    @Get("/v1/invoices")
    fun listInvoices(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoicesListResponse

    @Post("/v1/invoices")
    fun createInvoice(
        @Body body: CreateInvoiceRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoiceResponse

    @Get("/v1/invoices/{invoiceId}")
    fun retrieveInvoice(
        @PathVariable invoiceId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoiceResponse

    @Post("/v1/invoices/{invoiceId}")
    fun updateInvoice(
        @PathVariable invoiceId: String,
        @Body body: UpdateInvoiceRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoiceResponse

    @Delete("/v1/invoices/{invoiceId}")
    fun deleteInvoice(
        @PathVariable invoiceId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoiceDeleteResponse

    @Post("/v1/invoices/{invoiceId}/void")
    fun voidInvoice(
        @PathVariable invoiceId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoiceResponse

    @Post("/v1/invoices/{invoiceId}/finalize")
    fun finalizeInvoice(
        @PathVariable invoiceId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoiceResponse

    @Post("/v1/invoices/{invoiceId}/pay")
    fun payInvoice(
        @PathVariable invoiceId: String,
        @Body body: PayInvoiceRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoiceResponse

    @Post("/v1/invoices/{invoiceId}/mark_uncollectible")
    fun markInvoiceUncollectible(
        @PathVariable invoiceId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoiceResponse

    @Get("/v1/invoices/{invoiceId}/lines")
    fun getInvoiceLines(
        @PathVariable invoiceId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoiceLinesResponse

    @Get("/v1/invoices/upcoming")
    fun getUpcomingInvoice(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): InvoiceResponse

    @Get("/v1/invoices/upcoming/lines")
    fun getUpcomingInvoiceLines(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): UpcomingInvoiceLinesResponse
}
