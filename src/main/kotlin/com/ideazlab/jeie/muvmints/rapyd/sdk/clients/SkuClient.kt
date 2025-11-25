package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateSkuRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateSkuRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.SkuDeleteResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.SkuListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.SkuResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface SkuClient {

    @Get("/v1/skus")
    fun listSkus(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): SkuListResponse

    @Post("/v1/skus")
    fun createSku(
        @Body body: CreateSkuRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): SkuResponse

    @Get("/v1/skus/{skuId}")
    fun getSku(
        @PathVariable("skuId") skuId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): SkuResponse

    @Post("/v1/skus/{skuId}")
    fun updateSku(
        @PathVariable("skuId") skuId: String,
        @Body body: UpdateSkuRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): SkuResponse

    @Delete("/v1/skus/{skuId}")
    fun deleteSku(
        @PathVariable("skuId") skuId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): SkuDeleteResponse
}
