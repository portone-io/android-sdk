package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2PurchaseUnitShippingAddress
import kotlinx.parcelize.Parcelize

/**
 * 구매 상품 정보
 */
@Parcelize
data class PaypalV2PurchaseUnitShipping(
    /**
     * 수령지 정보
     */
    val address: PaypalV2PurchaseUnitShippingAddress?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "address" to address?.let { address.toJson() }
    )
}
