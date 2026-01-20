package io.portone.sdk.android

import org.json.JSONObject

// Map을 JSON 문자열로 변환하는 확장 함수
fun Map<String, Any?>.toJsonString(): String {
    return JSONObject(this).toString()
}
