package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus

@Response
data class CreateVirtualAccountResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)

@Response
data class SimulateBankTransferToVirtualAccountResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)

@Response
data class VirtualAccountResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)

@Response
data class UpdateRequestedCurrencyResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)

@Response
data class CloseVirtualAccountResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)

@Response
data class CapabilitiesOfVirtualAccountsResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)

@Response
data class RemitterDetailsResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)

@Response
data class TransactionDetailsResponse(
    val status: RapydStatus,
    val data: Map<String, Any?>
)
