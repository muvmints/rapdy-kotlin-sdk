package com.ideazlab.jeie.muvmints.rapyd.sdk.clients

import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.GetFxRateResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListCountriesApiResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListCurrenciesApiResponse
import com.ideazlab.jeie.muvmints.rapyd.sdk.api.responses.ListSupportedLanguagesApiResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("\${rapyd.base-url}")
interface LocalizationClient {

    @Get("/v1/fx_rates")
    fun getFxRate(
        @QueryValue("action_type") actionType: String,
        @QueryValue("buy_currency") buyCurrency: String,
        @QueryValue("sell_currency") sellCurrency: String,
        @QueryValue("amount") amount: String?,
        @QueryValue("fixed_side") fixedSide: String?,
        @QueryValue("date") date: String?,
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): GetFxRateResponse

    @Get("/v1/hosted/config/supported_languages")
    fun listSupportedLanguages(
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ListSupportedLanguagesApiResponse

    @Get("/v1/data/countries")
    fun listCountries(
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ListCountriesApiResponse

    @Get("/v1/data/currencies")
    fun listCurrencies(
        @Header("access_key") accessKey: String,
        @Header("salt") salt: String,
        @Header("timestamp") timestamp: String,
        @Header("signature") signature: String,
        @Header("idempotency") idempotency: String
    ): ListCurrenciesApiResponse
}
