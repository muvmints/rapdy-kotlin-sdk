package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.TestUtils
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateProductRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.ProductPackageDimensionsRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.UpdateProductRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.*
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.ProductClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class ProductServiceTest {
    private val config = TestUtils.testConfig()
    private val objectMapper = TestUtils.objectMapper()
    private val client: ProductClient = mock()
    private val service = ProductService(config, objectMapper, client)

    @Test
    fun listProducts_filtersAndSortsParams() {
        val input = mapOf("b" to "2", "a" to "1", "empty" to "", "blank" to "   ", "nullish" to null)
        val expected = linkedMapOf("a" to "1", "b" to "2")
        val stub = ProductListResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listProducts(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.listProducts(input)
        assertEquals(stub, res)

        verify(client).listProducts(
            params = eq(expected as Map<String, String?>),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun listProducts_emptyParams_passesNull() {
        val stub = ProductListResponse(RapydStatus(null, "OK", null, null, null), emptyList())
        whenever(client.listProducts(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        service.listProducts()

        verify(client).listProducts(
            params = isNull(),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun createProduct_passesBodyAndHeaders() {
        val body = CreateProductRequest(
            active = true,
            attributes = listOf("size", "color"),
            description = "Shirt",
            id = null,
            metadata = mapOf("k" to "v"),
            name = "T-Shirt",
            packageDimensions = ProductPackageDimensionsRequest(height = 1.0, length = 2.0, weight = 0.3, width = 1.0),
            shippable = true,
            statementDescriptor = "TSHIRT",
            type = "good",
            unitLabel = "pcs"
        )
        val stub = ProductResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydProduct(
                id = "pr_1",
                active = body.active,
                attributes = body.attributes,
                createdAt = null,
                description = body.description,
                images = emptyList(),
                metadata = body.metadata,
                name = body.name,
                packageDimensions = RapydProductPackageDimensions(
                    height = body.packageDimensions?.height,
                    length = body.packageDimensions?.length,
                    weight = body.packageDimensions?.weight,
                    width = body.packageDimensions?.width
                ),
                shippable = body.shippable,
                skus = emptyList(),
                statementDescriptor = body.statementDescriptor,
                type = body.type,
                unitLabel = body.unitLabel,
                updatedAt = null
            )
        )
        whenever(client.createProduct(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.createProduct(body)
        assertEquals(stub, res)

        verify(client).createProduct(
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun getProduct_passesHeaders() {
        val productId = "pr_get_1"
        val stub = ProductResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydProduct(
                id = productId,
                active = null,
                attributes = null,
                createdAt = null,
                description = null,
                images = null,
                metadata = null,
                name = null,
                packageDimensions = null,
                shippable = null,
                skus = null,
                statementDescriptor = null,
                type = null,
                unitLabel = null,
                updatedAt = null
            )
        )
        whenever(client.getProduct(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.getProduct(productId)
        assertEquals(stub, res)

        verify(client).getProduct(
            productId = eq(productId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun updateProduct_passesBodyAndHeaders() {
        val productId = "pr_upd_1"
        val body = UpdateProductRequest(
            active = false,
            attributes = listOf("size"),
            metadata = mapOf("note" to "updated"),
            name = "New Name",
            packageDimensions = ProductPackageDimensionsRequest(height = 2.0, length = 3.0, weight = 0.4, width = 2.0),
            statementDescriptor = "NEWNAME",
            unitLabel = "unit"
        )
        val stub = ProductResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydProduct(
                id = productId,
                active = body.active,
                attributes = body.attributes,
                createdAt = null,
                description = null,
                images = null,
                metadata = body.metadata,
                name = body.name,
                packageDimensions = RapydProductPackageDimensions(
                    height = body.packageDimensions?.height,
                    length = body.packageDimensions?.length,
                    weight = body.packageDimensions?.weight,
                    width = body.packageDimensions?.width
                ),
                shippable = null,
                skus = null,
                statementDescriptor = body.statementDescriptor,
                type = null,
                unitLabel = body.unitLabel,
                updatedAt = null
            )
        )
        whenever(client.updateProduct(any(), any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.updateProduct(productId, body)
        assertEquals(stub, res)

        verify(client).updateProduct(
            productId = eq(productId),
            body = eq(body),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }

    @Test
    fun deleteProduct_passesHeaders() {
        val productId = "pr_del_1"
        val stub = ProductDeleteResponse(
            RapydStatus(null, "OK", null, null, null),
            RapydDeletedItem(deleted = true, id = productId)
        )
        whenever(client.deleteProduct(any(), any(), any(), any(), any(), any())).thenReturn(stub)

        val res = service.deleteProduct(productId)
        assertEquals(stub, res)

        verify(client).deleteProduct(
            productId = eq(productId),
            accessKey = eq(config.accessKey),
            salt = any(),
            timestamp = any(),
            signature = any(),
            idempotency = any()
        )
    }
}
