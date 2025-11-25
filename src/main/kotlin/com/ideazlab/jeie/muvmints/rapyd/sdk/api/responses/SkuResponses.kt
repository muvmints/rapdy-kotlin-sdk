package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class SkuListResponse(
    val status: RapydStatus,
    val data: List<RapydSku>
)

@Response
data class SkuResponse(
    val status: RapydStatus,
    val data: RapydSku
)

@Response
data class SkuDeleteResponse(
    val status: RapydStatus,
    val data: RapydDeletedItem
)

@Serdeable
data class RapydSku(
    val id: String?,
    val active: Boolean?,
    val attributes: Map<String, Any?>?,
    @param:JsonProperty("created_at")
    val createdAt: Long?,
    val currency: String?,
    val image: String?,
    val inventory: RapydSkuInventory?,
    val metadata: Map<String, Any?>?,
    @param:JsonProperty("package_dimensions")
    val packageDimensions: RapydSkuPackageDimensions?,
    val price: Double?,
    val product: String?,
    @param:JsonProperty("updated_at")
    val updatedAt: Long?
)

@Serdeable
data class RapydSkuInventory(
    val type: String?,
    val quantity: Int?,
    val value: String?
)

@Serdeable
data class RapydSkuPackageDimensions(
    val height: Double?,
    val length: Double?,
    val weight: Double?,
    val width: Double?
)
