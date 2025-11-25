package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydCustomerPaymentMethod
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus

@Response
data class CustomerPaymentMethodsListResponse(
    val status: RapydStatus,
    val data: List<RapydCustomerPaymentMethod>
)

@Response
data class CustomerPaymentMethodResponse(
    val status: RapydStatus,
    val data: RapydCustomerPaymentMethod
)

@Response
data class DeleteCustomerPaymentMethodResponse(
    val status: RapydStatus,
    val data: DeleteResult
)

data class DeleteResult(
    val id: String?,
    val deleted: Boolean?
)
