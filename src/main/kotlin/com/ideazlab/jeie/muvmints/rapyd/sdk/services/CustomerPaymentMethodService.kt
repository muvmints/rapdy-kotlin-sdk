package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.AddCustomerPaymentMethodRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateCustomerPaymentMethodRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CustomerPaymentMethodResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CustomerPaymentMethodsListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.DeleteCustomerPaymentMethodResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CustomerPaymentMethodClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class CustomerPaymentMethodService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: CustomerPaymentMethodClient
) : BaseService() {

    fun listCustomerPaymentMethods(
        customerId: String,
        params: Map<String, String?> = emptyMap()
    ): CustomerPaymentMethodsListResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/customers/").append(customerId).append("/payment_methods")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listCustomerPaymentMethods(
            customerId = customerId,
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun addCustomerPaymentMethod(
        customerId: String,
        body: AddCustomerPaymentMethodRequest
    ): CustomerPaymentMethodResponse {
        val path = "/v1/customers/$customerId/payment_methods"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.addCustomerPaymentMethod(
            customerId = customerId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveCustomerPaymentMethod(
        customerId: String,
        pmtId: String
    ): CustomerPaymentMethodResponse {
        val path = "/v1/customers/$customerId/payment_methods/$pmtId"
        val signed = sign("get", path, null, config)
        return client.retrieveCustomerPaymentMethod(
            customerId = customerId,
            pmtId = pmtId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updateCustomerPaymentMethod(
        customerId: String,
        pmtId: String,
        body: UpdateCustomerPaymentMethodRequest
    ): CustomerPaymentMethodResponse {
        val path = "/v1/customers/$customerId/payment_methods/$pmtId"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updateCustomerPaymentMethod(
            customerId = customerId,
            pmtId = pmtId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun deleteCustomerPaymentMethod(
        customerId: String,
        pmtId: String
    ): DeleteCustomerPaymentMethodResponse {
        val path = "/v1/customers/$customerId/payment_methods/$pmtId"
        val signed = sign("delete", path, null, config)
        return client.deleteCustomerPaymentMethod(
            customerId = customerId,
            pmtId = pmtId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
