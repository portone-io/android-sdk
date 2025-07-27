package io.portone.sdk.__generated__.entity.bypass

import io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI.PaypalV2LoadIssueBillingKeyUIBypass

data class LoadIssueBillingKeyUIBypass(
    /**
     * **Paypal bypass 파라미터**
     */
    val paypalV2: PaypalV2LoadIssueBillingKeyUIBypass?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "paypal_v2" to paypalV2?.let { paypalV2.toJson() }
    )
}
