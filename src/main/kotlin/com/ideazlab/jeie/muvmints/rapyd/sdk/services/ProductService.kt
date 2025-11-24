package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateProductRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateProductRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ProductDeleteResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ProductListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ProductResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.ProductClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class ProductService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: ProductClient
) : BaseService() {

    fun listProducts(params: Map<String, String?> = emptyMap()): ProductListResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/products")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listProducts(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun createProduct(body: CreateProductRequest): ProductResponse {
        val path = "/v1/products"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createProduct(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun getProduct(productId: String): ProductResponse {
        val path = "/v1/products/$productId"
        val signed = sign("get", path, null, config)
        return client.getProduct(
            productId = productId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun updateProduct(productId: String, body: UpdateProductRequest): ProductResponse {
        val path = "/v1/products/$productId"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.updateProduct(
            productId = productId,
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun deleteProduct(productId: String): ProductDeleteResponse {
        val path = "/v1/products/$productId"
        val signed = sign("delete", path, null, config)
        return client.deleteProduct(
            productId = productId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
