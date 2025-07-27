package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.PaypalV2PaymentSourcePaypal

data class PaypalV2PaymentSource(
    val paypal: PaypalV2PaymentSourcePaypal?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "paypal" to paypal?.let { paypal.toJson() }
    )
}
