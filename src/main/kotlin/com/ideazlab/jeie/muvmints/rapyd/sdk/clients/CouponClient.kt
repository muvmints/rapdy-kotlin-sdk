package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateCouponRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateCouponRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CouponDeleteResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CouponListResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.CouponResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface CouponClient {

    @Get("/v1/coupons")
    fun listCoupons(
        @QueryValue params: Map<String, String?>?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CouponListResponse

    @Post("/v1/coupons")
    fun createCoupon(
        @Body body: CreateCouponRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CouponResponse

    @Get("/v1/coupons/{couponId}")
    fun retrieveCoupon(
        @PathVariable("couponId") couponId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CouponResponse

    @Post("/v1/coupons/{couponId}")
    fun updateCoupon(
        @PathVariable("couponId") couponId: String,
        @Body body: UpdateCouponRequest,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CouponResponse

    @Delete("/v1/coupons/{couponId}")
    fun deleteCoupon(
        @PathVariable("couponId") couponId: String,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): CouponDeleteResponse
}
