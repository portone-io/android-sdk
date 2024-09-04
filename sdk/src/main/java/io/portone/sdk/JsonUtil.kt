package io.portone.sdk

import kotlinx.serialization.json.Json

val encodingformat = Json {
    explicitNulls = false
    encodeDefaults = true
    ignoreUnknownKeys = true
}