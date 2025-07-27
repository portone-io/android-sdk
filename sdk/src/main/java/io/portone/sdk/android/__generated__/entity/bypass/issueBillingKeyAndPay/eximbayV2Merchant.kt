package io.portone.sdk.__generated__.entity.bypass.issueBillingKeyAndPay

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EximbayV2Merchant(
    /**
     * **상점명**
     */
    val shop: String
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "shop" to shop
    )
}
