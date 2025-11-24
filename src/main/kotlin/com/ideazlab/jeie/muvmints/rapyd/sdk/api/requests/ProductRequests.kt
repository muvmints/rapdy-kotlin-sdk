package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateProductRequest(
    val active: Boolean? = null,
    val attributes: List<String>? = null,
    val description: String? = null,
    val id: String? = null,
    val metadata: Map<String, Any?>? = null,
    val name: String,
    @param:JsonProperty("package_dimensions")
    val packageDimensions: ProductPackageDimensionsRequest? = null,
    val shippable: Boolean? = null,
    @param:JsonProperty("statement_descriptor")
    val statementDescriptor: String? = null,
    val type: String,
    @param:JsonProperty("unit_label")
    val unitLabel: String? = null
)

@Serdeable
data class UpdateProductRequest(
    val active: Boolean? = null,
    val attributes: List<String>? = null,
    val metadata: Map<String, Any?>? = null,
    val name: String? = null,
    @param:JsonProperty("package_dimensions")
    val packageDimensions: ProductPackageDimensionsRequest? = null,
    @param:JsonProperty("statement_descriptor")
    val statementDescriptor: String? = null,
    @param:JsonProperty("unit_label")
    val unitLabel: String? = null
)

@Serdeable
data class ProductPackageDimensionsRequest(
    val height: Double? = null,
    val length: Double? = null,
    val weight: Double? = null,
    val width: Double? = null
)
