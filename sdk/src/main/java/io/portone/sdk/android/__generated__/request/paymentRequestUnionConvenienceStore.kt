package io.portone.sdk.__generated__.request

import io.portone.sdk.__generated__.request.PaymentRequestUnionConvenienceStorePaymentDeadline

data class PaymentRequestUnionConvenienceStore(
    /**
     * **편의점결제 지불기한**
     */
    val paymentDeadline: PaymentRequestUnionConvenienceStorePaymentDeadline?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "paymentDeadline" to paymentDeadline?.let { paymentDeadline.toJson() }
    )
}
