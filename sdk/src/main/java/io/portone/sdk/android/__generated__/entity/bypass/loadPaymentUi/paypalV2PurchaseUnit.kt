package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2PurchaseUnitShipping
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaypalV2PurchaseUnit(
    /**
     * 구매 상품 정보
     */
    val shipping: PaypalV2PurchaseUnitShipping?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "shipping" to shipping?.let { shipping.toJson() }
    )
}
