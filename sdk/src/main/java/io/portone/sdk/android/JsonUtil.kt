package io.portone.sdk.android

import kotlinx.serialization.json.Json

val encodingformat = Json {
    explicitNulls = false
    encodeDefaults = true
    ignoreUnknownKeys = true
}