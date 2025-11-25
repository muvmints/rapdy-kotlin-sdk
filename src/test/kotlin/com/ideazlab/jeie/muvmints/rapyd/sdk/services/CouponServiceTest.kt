package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateCouponRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateCouponRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.CouponClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class CouponServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: CouponClient = mock()
    private val service = CouponService(config, objectMapper, client)

    @Test
    fun listCoupons_filtersAndSortsParams() {
        val input = mapOf("b" to "2", "a" to "1", "empty" to "", "nullish" to null)
        val expected = linkedMapOf("a" to "1", "b" to "2")
        val stub = CouponListResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listCoupons(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listCoupons(input)
        assertEquals(stub, res)

        verify(client).listCoupons(
            params = eq(expected as Map<String, String?>),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listCoupons_emptyParams_passesNull() {
        val stub = CouponListResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listCoupons(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        service.listCoupons()

        verify(client).listCoupons(
            params = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createCoupon_passesBodyAndHeaders() {
        val body = CreateCouponRequest(amountOff = 10.0, currency = "USD", duration = "forever")
        val stub = CouponResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydCoupon(
                id = "cp_1",
                amountOff = 10.0,
                currency = "USD",
                description = null,
                discountDurationInUses = null,
                discountValidUntil = null,
                discountValidityInMonths = null,
                duration = "forever",
                durationInMonths = null,
                maxRedemptions = null,
                metadata = null,
                percentOff = null,
                redeemBy = null,
                timesRedeemed = null,
                valid = true,
                created = null
            )
        )
        whenever(client.createCoupon(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createCoupon(body)
        assertEquals(stub, res)

        verify(client).createCoupon(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun retrieveCoupon_passesHeaders() {
        val couponId = "cp_2"
        val stub = CouponResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydCoupon(
                id = couponId,
                amountOff = null,
                currency = null,
                description = null,
                discountDurationInUses = null,
                discountValidUntil = null,
                discountValidityInMonths = null,
                duration = null,
                durationInMonths = null,
                maxRedemptions = null,
                metadata = null,
                percentOff = null,
                redeemBy = null,
                timesRedeemed = null,
                valid = null,
                created = null
            )
        )
        whenever(client.retrieveCoupon(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.retrieveCoupon(couponId)
        assertEquals(stub, res)

        verify(client).retrieveCoupon(
            couponId = eq(couponId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updateCoupon_passesBodyAndHeaders() {
        val couponId = "cp_3"
        val body = UpdateCouponRequest(metadata = mapOf("x" to "y"), description = "desc")
        val stub = CouponResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydCoupon(
                id = couponId,
                amountOff = null,
                currency = null,
                description = "desc",
                discountDurationInUses = null,
                discountValidUntil = null,
                discountValidityInMonths = null,
                duration = null,
                durationInMonths = null,
                maxRedemptions = null,
                metadata = body.metadata,
                percentOff = null,
                redeemBy = null,
                timesRedeemed = null,
                valid = null,
                created = null
            )
        )
        whenever(client.updateCoupon(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updateCoupon(couponId, body)
        assertEquals(stub, res)

        verify(client).updateCoupon(
            couponId = eq(couponId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deleteCoupon_passesHeaders() {
        val couponId = "cp_4"
        val stub = CouponDeleteResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydDeletedItem(deleted = true, id = couponId)
        )
        whenever(client.deleteCoupon(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deleteCoupon(couponId)
        assertEquals(stub, res)

        verify(client).deleteCoupon(
            couponId = eq(couponId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
