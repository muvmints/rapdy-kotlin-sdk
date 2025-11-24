package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class ProductListResponse(
    val status: RapydStatus,
    val data: List<RapydProduct>
)

@Response
data class ProductResponse(
    val status: RapydStatus,
    val data: RapydProduct
)

@Response
data class ProductDeleteResponse(
    val status: RapydStatus,
    val data: RapydDeletedItem
)

@Serdeable
data class RapydDeletedItem(
    val deleted: Boolean?,
    val id: String?
)

@Serdeable
data class RapydProduct(
    val id: String?,
    val active: Boolean?,
    val attributes: List<String>?,
    @param:JsonProperty("created_at")
    val createdAt: Long?,
    val description: String?,
    val images: List<String>?,
    val metadata: Map<String, Any?>?,
    val name: String?,
    @param:JsonProperty("package_dimensions")
    val packageDimensions: RapydProductPackageDimensions?,
    val shippable: Boolean?,
    val skus: List<Any>?,
    @param:JsonProperty("statement_descriptor")
    val statementDescriptor: String?,
    val type: String?,
    @param:JsonProperty("unit_label")
    val unitLabel: String?,
    @param:JsonProperty("updated_at")
    val updatedAt: Long?
)

@Serdeable
data class RapydProductPackageDimensions(
    val height: Double?,
    val length: Double?,
    val weight: Double?,
    val width: Double?
)
