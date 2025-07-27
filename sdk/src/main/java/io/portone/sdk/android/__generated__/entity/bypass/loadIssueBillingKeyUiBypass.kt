package io.portone.sdk.__generated__.entity.bypass

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI.PaypalV2LoadIssueBillingKeyUIBypass
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoadIssueBillingKeyUIBypass(
    /**
     * **Paypal bypass 파라미터**
     */
    val paypalV2: PaypalV2LoadIssueBillingKeyUIBypass?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "paypal_v2" to paypalV2?.let { paypalV2.toJson() }
    )
}
