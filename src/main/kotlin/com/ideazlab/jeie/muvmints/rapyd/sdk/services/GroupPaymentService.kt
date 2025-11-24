package com.ideazlab.jeie.muvmints.rapyd.sdk.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideazlab.jeie.muvmints.rapyd.sdk.BaseService
import com.ideazlab.jeie.muvmints.rapyd.sdk.RapydConfig
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.CreateGroupPaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.requests.RefundGroupPaymentRequest
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.GroupPaymentRefundResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.GroupPaymentResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.clients.GroupPaymentClient
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

@Singleton
@Requires(bean = RapydConfig::class)
class GroupPaymentService(
    private val config: RapydConfig,
    private val objectMapper: ObjectMapper,
    private val client: GroupPaymentClient
) : BaseService() {

    fun createGroupPayment(body: CreateGroupPaymentRequest): GroupPaymentResponse {
        val path = "/v1/payments/group_payments"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.createGroupPayment(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun retrieveGroupPayment(groupPaymentId: String): GroupPaymentResponse {
        val path = "/v1/payments/group_payments/$groupPaymentId"
        val signed = sign("get", path, null, config)
        return client.retrieveGroupPayment(
            groupPaymentId = groupPaymentId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun cancelGroupPayment(groupPaymentId: String): GroupPaymentResponse {
        val path = "/v1/payments/group_payments/$groupPaymentId"
        val signed = sign("delete", path, null, config)
        return client.cancelGroupPayment(
            groupPaymentId = groupPaymentId,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }

    fun refundGroupPayment(body: RefundGroupPaymentRequest): GroupPaymentRefundResponse {
        val path = "/v1/refunds/group_payments"
        val jsonBody = objectMapper.writeValueAsString(body).replace("\\/", "/")
        val signed = sign("post", path, jsonBody, config)
        return client.refundGroupPayment(
            body = body,
            accessKey = config.accessKey,
            salt = signed.salt,
            timestamp = signed.timestamp.toString(),
            signature = signed.signature,
            idempotency = signed.idempotency
        )
    }
}
