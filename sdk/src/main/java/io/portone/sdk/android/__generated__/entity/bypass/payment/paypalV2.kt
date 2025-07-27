package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2AdditionalData
import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2Payer
import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2PurchaseUnit
import io.portone.sdk.__generated__.entity.bypass.payment.PaypalV2PaymentSource

/**
 * **Paypal bypass 파라미터**
 */
data class PaypalV2PaymentBypass(
    /**
     * create order API 호출에 필요한 파라미터
     */
    val purchaseUnits: List<PaypalV2PurchaseUnit>?,
    val payer: PaypalV2Payer?,
    val paymentSource: PaypalV2PaymentSource?,
    /**
     * STC 파라미터
     */
    val additionalData: List<PaypalV2AdditionalData>?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "purchase_units" to purchaseUnits?.let { purchaseUnits.map { it.toJson() } },
        "payer" to payer?.let { payer.toJson() },
        "payment_source" to paymentSource?.let { paymentSource.toJson() },
        "additional_data" to additionalData?.let { additionalData.map { it.toJson() } }
    )
}
