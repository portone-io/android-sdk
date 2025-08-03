package io.portone.sdk.android

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonUnquotedLiteral
import org.json.JSONObject

val encodingformat = Json {
    explicitNulls = false
    encodeDefaults = true
    ignoreUnknownKeys = true
}

// Map을 JSON 문자열로 변환하는 확장 함수
fun Map<String, Any?>.toJsonString(): String {
    return JSONObject(this).toString()
}

internal object RawJsonStringSerializer : KSerializer<String> {

    override val descriptor = PrimitiveSerialDescriptor("io.portone.sdk.android", PrimitiveKind.STRING)

    /**
     * Encodes [value] using [JsonUnquotedLiteral], if [encoder] is a [JsonEncoder],
     * or with [Encoder.encodeString] otherwise.
     */
    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: String) = when (encoder) {
        is JsonEncoder -> encoder.encodeJsonElement(JsonUnquotedLiteral(value))
        else -> encoder.encodeString(value)
    }

    /**
     * If [decoder] is a [JsonDecoder], decodes a [kotlinx.serialization.json.JsonElement] (which could be an object,
     * array, or primitive) as a string.
     *
     * Otherwise, decode a string using [Decoder.decodeString].
     */
    override fun deserialize(decoder: Decoder): String = when (decoder) {
        is JsonDecoder -> decoder.decodeJsonElement().toString()
        else -> decoder.decodeString()
    }
}