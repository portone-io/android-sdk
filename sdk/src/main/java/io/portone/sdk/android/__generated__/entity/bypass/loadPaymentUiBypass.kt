package io.portone.sdk.__generated__.entity.bypass

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.loadPaymentUI.PaypalV2LoadPaymentUIBypass
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoadPaymentUIBypass(
    /**
     * **Paypal bypass 파라미터**
     */
    val paypalV2: PaypalV2LoadPaymentUIBypass?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "paypal_v2" to paypalV2?.let { paypalV2.toJson() }
    )
}
