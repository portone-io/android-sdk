package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2PurchaseUnitShipping

data class PaypalV2PurchaseUnit(
    /**
     * 구매 상품 정보
     */
    val shipping: PaypalV2PurchaseUnitShipping?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "shipping" to shipping?.let { shipping.toJson() }
    )
}
