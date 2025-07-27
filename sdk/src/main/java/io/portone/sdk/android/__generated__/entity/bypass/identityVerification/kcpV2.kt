package io.portone.sdk.__generated__.entity.bypass.identityVerification

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * **KCP bypass 파라미터**
 */
@Parcelize
data class KcpV2IdentityVerificationBypass(
    /**
     * **DI 생성 시 사용할 사이트 ID**
     */
    val webSiteid: String?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "web_siteid" to webSiteid?.let { webSiteid }
    )
}
