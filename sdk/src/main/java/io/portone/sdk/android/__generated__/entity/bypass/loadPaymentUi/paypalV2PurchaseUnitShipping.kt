package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2PurchaseUnitShippingAddress

/**
 * 구매 상품 정보
 */
data class PaypalV2PurchaseUnitShipping(
    /**
     * 수령지 정보
     */
    val address: PaypalV2PurchaseUnitShippingAddress?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "address" to address?.let { address.toJson() }
    )
}
