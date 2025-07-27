package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

data class PaypalV2AdditionalData(
    val key: String,
    val `value`: String
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "key" to key,
        "value" to `value`
    )
}
