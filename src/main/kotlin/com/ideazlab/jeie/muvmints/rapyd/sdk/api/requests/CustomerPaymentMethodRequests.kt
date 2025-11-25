package com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class AddCustomerPaymentMethodRequest(
    val data: Map<String, Any?>
)

@Serdeable
data class UpdateCustomerPaymentMethodRequest(
    val id: String? = null,
    val type: String? = null,
    val category: String? = null,
    val name: String? = null,
    val image: String? = null,
    val token: String? = null,
    val last4: String? = null,
    val fingerprint_token: String? = null,
    val network_reference_id: String? = null,
    val redirect_url: String? = null,
    val webhook_url: String? = null,
    val supporting_documentation: String? = null,
    val next_action: Any? = null,
    val metadata: Map<String, Any?>? = null
)
