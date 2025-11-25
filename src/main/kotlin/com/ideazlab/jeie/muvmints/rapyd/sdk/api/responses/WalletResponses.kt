package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydWallet
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydContact

@Response
data class WalletListResponse(
    val status: RapydStatus,
    val data: List<RapydWallet>
)

@Response
data class WalletActionResponse(
    val status: RapydStatus
)

@Response
data class ContactListResponse(
    val status: RapydStatus,
    val data: List<RapydContact>
)

@Response
data class ContactResponse(
    val status: RapydStatus,
    val data: RapydContact
)

@Response
data class DeleteContactResponse(
    val status: RapydStatus,
    val data: DeleteContactData
)

data class DeleteContactData(
    val delete: Boolean?,
    val id: String?
)

@Response
data class ComplianceLevelsResponse(
    val status: RapydStatus,
    val data: ComplianceLevelsData
)

data class ComplianceLevelsData(
    @com.fasterxml.jackson.annotation.JsonProperty("compliance_levels")
    val complianceLevels: List<ComplianceLevel>
)

data class ComplianceLevel(
    val level: Int?,
    val elements: List<ComplianceElement>?
)

data class ComplianceElement(
    @com.fasterxml.jackson.annotation.JsonProperty("element_name")
    val elementName: String?,
    val verified: Boolean?
)
