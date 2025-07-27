package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * **네이버페이 bypass 파라미터**
 */
@Parcelize
data class NaverpayIssueBillingKeyBypass(
    /**
     * 하부 가맹점 명
     */
    val subMerchantName: String?,
    /**
     * 하부 가맹점 ID
     */
    val subMerchantId: String?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "subMerchantName" to subMerchantName?.let { subMerchantName },
        "subMerchantId" to subMerchantId?.let { subMerchantId }
    )
}
