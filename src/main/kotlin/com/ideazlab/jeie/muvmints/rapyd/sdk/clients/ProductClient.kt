package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateProductRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateProductRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ProductDeleteResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ProductListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ProductResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface ProductClient {

    @Get("/v1/products")
    fun listProducts(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ProductListResponse

    @Post("/v1/products")
    fun createProduct(
        @Body body: CreateProductRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ProductResponse

    @Get("/v1/products/{productId}")
    fun getProduct(
        @PathVariable("productId") productId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ProductResponse

    @Post("/v1/products/{productId}")
    fun updateProduct(
        @PathVariable("productId") productId: String,
        @Body body: UpdateProductRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ProductResponse

    @Delete("/v1/products/{productId}")
    fun deleteProduct(
        @PathVariable("productId") productId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ProductDeleteResponse
}
