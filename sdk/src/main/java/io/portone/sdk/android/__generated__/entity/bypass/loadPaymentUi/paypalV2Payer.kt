package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2PayerAddress
import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2PayerTaxInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaypalV2Payer(
    /**
     * 구매자 정보
     */
    val taxInfo: PaypalV2PayerTaxInfo?,
    val address: PaypalV2PayerAddress?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "tax_info" to taxInfo?.let { taxInfo.toJson() },
        "address" to address?.let { address.toJson() }
    )
}
