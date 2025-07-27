package io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI

import io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI.PaypalV2AdditionalData
import io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI.PaypalV2ShippingAddress
import io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI.PaypalV2Style

/**
 * **Paypal bypass 파라미터**
 */
data class PaypalV2LoadIssueBillingKeyUIBypass(
    /**
     * 페이팔 빌링키 발급 UI 호출 시 필요한 파라미터
     */
    val style: PaypalV2Style?,
    val shippingAddress: PaypalV2ShippingAddress?,
    /**
     * STC 파라미터
     */
    val additionalData: List<PaypalV2AdditionalData>?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "style" to style?.let { style.toJson() },
        "shipping_address" to shippingAddress?.let { shippingAddress.toJson() },
        "additional_data" to additionalData?.let { additionalData.map { it.toJson() } }
    )
}
