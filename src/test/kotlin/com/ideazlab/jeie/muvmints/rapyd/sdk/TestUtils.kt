package com.ideazlab.jeie.muvmints.rapyd.sdk

import com.fasterxml.jackson.databind.ObjectMapper

object TestUtils {
    fun testConfig(): RapydConfig = RapydConfig().apply {
        baseUrl = "https://sandbox.rapyd.net"
        accessKey = "test_access_key"
        secretKey = "test_secret_key"
        timeoutMs = 1000
    }

    fun objectMapper(): ObjectMapper = ObjectMapper()
}
