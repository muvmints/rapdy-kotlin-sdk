package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateSkuRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.SkuInventoryRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.SkuPackageDimensionsRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateSkuRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.SkuClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class SkuServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: SkuClient = mock()
    private val service = SkuService(config, objectMapper, client)

    @Test
    fun listSkus_filtersAndSortsParams() {
        val input = mapOf("z" to "9", "a" to "1", "empty" to "", "blank" to "   ", "nullish" to null)
        val expected = linkedMapOf("a" to "1", "z" to "9")
        val stub = SkuListResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listSkus(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listSkus(input)
        assertEquals(stub, res)

        verify(client).listSkus(
            params = eq(expected as Map<String, String?>),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listSkus_emptyParams_passesNull() {
        val stub = SkuListResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listSkus(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        service.listSkus()

        verify(client).listSkus(
            params = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createSku_passesBodyAndHeaders() {
        val body = CreateSkuRequest(
            currency = "USD",
            inventory = SkuInventoryRequest(type = "finite", quantity = 10, value = null),
            price = 19.99,
            product = "pr_1",
            active = true,
            attributes = listOf("size"),
            image = "https://img",
            metadata = mapOf("k" to "v"),
            packageDimensions = SkuPackageDimensionsRequest(length = 2.0, height = 1.0, weight = 0.5, width = 1.0)
        )
        val stub = SkuResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydSku(
                id = "sku_1",
                active = body.active,
                attributes = mapOf("size" to null),
                createdAt = null,
                currency = body.currency,
                image = body.image,
                inventory = RapydSkuInventory(
                    type = body.inventory.type,
                    quantity = body.inventory.quantity,
                    value = body.inventory.value
                ),
                metadata = body.metadata,
                packageDimensions = RapydSkuPackageDimensions(
                    height = body.packageDimensions?.height,
                    length = body.packageDimensions?.length,
                    weight = body.packageDimensions?.weight,
                    width = body.packageDimensions?.width
                ),
                price = body.price,
                product = body.product,
                updatedAt = null
            )
        )
        whenever(client.createSku(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createSku(body)
        assertEquals(stub, res)

        verify(client).createSku(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getSku_passesHeaders() {
        val skuId = "sku_get_1"
        val stub = SkuResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydSku(
                id = skuId,
                active = null,
                attributes = null,
                createdAt = null,
                currency = null,
                image = null,
                inventory = null,
                metadata = null,
                packageDimensions = null,
                price = null,
                product = null,
                updatedAt = null
            )
        )
        whenever(client.getSku(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getSku(skuId)
        assertEquals(stub, res)

        verify(client).getSku(
            skuId = eq(skuId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updateSku_passesBodyAndHeaders() {
        val skuId = "sku_upd_1"
        val body = UpdateSkuRequest(
            currency = "EUR",
            inventory = SkuInventoryRequest(type = "finite", quantity = 20, value = null),
            price = 29.99,
            product = "pr_2",
            active = false,
            attributes = listOf("color"),
            image = "https://img2",
            metadata = mapOf("note" to "updated"),
            packageDimensions = SkuPackageDimensionsRequest(length = 3.0, height = 2.0, weight = 0.7, width = 1.5)
        )
        val stub = SkuResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydSku(
                id = skuId,
                active = body.active,
                attributes = mapOf("color" to null),
                createdAt = null,
                currency = body.currency,
                image = body.image,
                inventory = RapydSkuInventory(
                    type = body.inventory?.type,
                    quantity = body.inventory?.quantity,
                    value = body.inventory?.value
                ),
                metadata = body.metadata,
                packageDimensions = RapydSkuPackageDimensions(
                    height = body.packageDimensions?.height,
                    length = body.packageDimensions?.length,
                    weight = body.packageDimensions?.weight,
                    width = body.packageDimensions?.width
                ),
                price = body.price,
                product = body.product,
                updatedAt = null
            )
        )
        whenever(client.updateSku(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updateSku(skuId, body)
        assertEquals(stub, res)

        verify(client).updateSku(
            skuId = eq(skuId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deleteSku_passesHeaders() {
        val skuId = "sku_del_1"
        val stub =
            SkuDeleteResponse(RapydStatus(null, "OK", null, null, null), RapydDeletedItem(deleted = true, id = skuId))
        whenever(client.deleteSku(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deleteSku(skuId)
        assertEquals(stub, res)

        verify(client).deleteSku(
            skuId = eq(skuId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
