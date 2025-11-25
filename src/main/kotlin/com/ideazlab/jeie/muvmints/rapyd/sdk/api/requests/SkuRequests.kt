package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateSkuRequest(
    val currency: String,
    val inventory: SkuInventoryRequest,
    val price: Double,
    val product: String,
    val active: Boolean? = null,
    val attributes: List<String>? = null,
    val image: String? = null,
    val metadata: Map<String, Any?>? = null,
    @param:JsonProperty("package_dimensions")
    val packageDimensions: SkuPackageDimensionsRequest? = null
)

@Serdeable
data class UpdateSkuRequest(
    val currency: String? = null,
    val inventory: SkuInventoryRequest? = null,
    val price: Double? = null,
    val product: String? = null,
    val active: Boolean? = null,
    val attributes: List<String>? = null,
    val image: String? = null,
    val metadata: Map<String, Any?>? = null,
    @param:JsonProperty("package_dimensions")
    val packageDimensions: SkuPackageDimensionsRequest? = null
)

@Serdeable
data class SkuInventoryRequest(
    val type: String? = null, // finite | infinite | bucket
    val quantity: Int? = null,
    val value: String? = null // in_stock | limited | out_of_stock
)

@Serdeable
data class SkuPackageDimensionsRequest(
    val length: Double? = null,
    val height: Double? = null,
    val weight: Double? = null,
    val width: Double? = null
)
