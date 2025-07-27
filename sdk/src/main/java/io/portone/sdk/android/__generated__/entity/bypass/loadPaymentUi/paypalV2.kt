package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2AdditionalData
import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2Payer
import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2PurchaseUnit
import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2Style

/**
 * **Paypal bypass 파라미터**
 */
data class PaypalV2LoadPaymentUIBypass(
    /**
     * SPB 버튼 렌더링에 필요한 파라미터
     */
    val style: PaypalV2Style?,
    /**
     * 허용할 결제 수단 (예: "card, credit, bancontact")
     */
    val enableFunding: String?,
    /**
     * 차단할 결제 수단 (예: "venmo, mercadopago")
     */
    val disableFunding: String?,
    /**
     * create order API 호출에 필요한 파라미터
     */
    val purchaseUnits: List<PaypalV2PurchaseUnit>?,
    val payer: PaypalV2Payer?,
    /**
     * STC 파라미터
     */
    val additionalData: List<PaypalV2AdditionalData>?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "style" to style?.let { style.toJson() },
        "enable-funding" to enableFunding?.let { enableFunding },
        "disable-funding" to disableFunding?.let { disableFunding },
        "purchase_units" to purchaseUnits?.let { purchaseUnits.map { it.toJson() } },
        "payer" to payer?.let { payer.toJson() },
        "additional_data" to additionalData?.let { additionalData.map { it.toJson() } }
    )
}
