package com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.ideazlab.jeie.muvmints.rapyd.sdk.Response
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.embedded.RapydStatus
import io.micronaut.serde.annotation.Serdeable

@Response
data class GetFxRateResponse(
    val status: RapydStatus,
    val data: RapydDailyRate
)

@Response
data class ListSupportedLanguagesApiResponse(
    val status: RapydStatus,
    val data: RapydListSupportedLanguagesData
)

@Response
data class ListCountriesApiResponse(
    val status: RapydStatus,
    val data: RapydListCountriesData
)

@Response
data class ListCurrenciesApiResponse(
    val status: RapydStatus,
    val data: List<RapydCurrencyInfo>
)

// ---- Embedded data structures for Localization ----

@Serdeable
data class RapydDailyRate(
    @JsonProperty("action_type") val actionType: String?,
    @JsonProperty("buy_amount") val buyAmount: Double?,
    @JsonProperty("buy_currency") val buyCurrency: String?,
    val date: String?,
    @JsonProperty("fixed_side") val fixedSide: String?,
    val rate: Double?,
    @JsonProperty("sell_amount") val sellAmount: Double?,
    @JsonProperty("sell_currency") val sellCurrency: String?
)

@Serdeable
data class RapydListSupportedLanguagesData(
    val languages: List<RapydSupportedLanguage>
)

@Serdeable
data class RapydSupportedLanguage(
    val name: String?,
    @JsonProperty("iso_alpha2") val isoAlpha2: String?
)

@Serdeable
data class RapydListCountriesData(
    // The OpenAPI names this property 'languages' although it contains countries.
    // We keep the original name to match the API.
    val languages: List<RapydCountryInfo>
)

@Serdeable
data class RapydCountryInfo(
    @JsonProperty("currency_code") val currencyCode: String?,
    @JsonProperty("currency_name") val currencyName: String?,
    @JsonProperty("currency_sign") val currencySign: String?,
    val id: String?,
    @JsonProperty("iso_alpha2") val isoAlpha2: String?,
    @JsonProperty("iso_alpha3") val isoAlpha3: String?,
    val name: String?,
    @JsonProperty("phone_code") val phoneCode: String?
)

@Serdeable
data class RapydCurrencyInfo(
    val code: String?,
    @JsonProperty("digits_after_decimal_separator") val digitsAfterDecimalSeparator: String?,
    val name: String?,
    @JsonProperty("numeric_code") val numericCode: String?,
    val symbol: String?
)
