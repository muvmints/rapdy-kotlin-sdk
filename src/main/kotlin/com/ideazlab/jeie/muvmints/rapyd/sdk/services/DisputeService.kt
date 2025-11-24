package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.DisputeResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListDisputesResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.DisputeClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class DisputeService(
    private val config: RapydConfig,
    private val client: DisputeClient
) : BaseService() {

    fun listDisputes(params: Map<String, String?> = emptyMap()): ListDisputesResponse {
        val filtered = params.filterValues { !it.isNullOrBlank() }
        val ordered = linkedMapOf<String, String>()
        filtered.keys.sorted().forEach { k -> ordered[k] = filtered[k]!! }
        val query = if (ordered.isNotEmpty()) ordered.entries.joinToString("&") { (k, v) -> "$k=$v" } else null
        val pathWithQuery = buildString {
            append("/v1/disputes")
            if (!query.isNullOrBlank()) append("?").append(query)
        }
        val signed = sign("get", pathWithQuery, null, config)
        return client.listDisputes(
            params = if (ordered.isEmpty()) null else ordered,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveDispute(disputeId: String): DisputeResponse {
        val path = "/v1/disputes/$disputeId"
        val signed = sign("get", path, null, config)
        return client.retrieveDispute(
            disputeId = disputeId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
