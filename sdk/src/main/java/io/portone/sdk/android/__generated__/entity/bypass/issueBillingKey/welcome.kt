package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * **웰컴페이먼츠 bypass 파라미터**
 */
@Parcelize
data class WelcomeIssueBillingKeyBypass(
    /**
     * 메인 로고 URL (크기: 89x19)
     */
    val logoUrl: String?,
    /**
     * 서브 로고 URL (크기: 64x13)
     */
    val logo2Nd: String?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "logo_url" to logoUrl?.let { logoUrl },
        "logo_2nd" to logo2Nd?.let { logo2Nd }
    )
}
