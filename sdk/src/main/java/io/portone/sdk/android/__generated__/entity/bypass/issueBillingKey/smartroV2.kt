package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.issueBillingKey.SmartroV2IsPwdPass
import io.portone.sdk.__generated__.entity.bypass.issueBillingKey.SmartroV2SkinColor
import kotlinx.parcelize.Parcelize

/**
 * **스마트로 bypass 파라미터**
 */
@Parcelize
data class SmartroV2IssueBillingKeyBypass(
    /**
     * UI 스타일(기본: RED)
     */
    val skinColor: SmartroV2SkinColor?,
    /**
     * 결제 비밀번호 등록 Skip 여부
     */
    val isPwdPass: SmartroV2IsPwdPass?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "SkinColor" to skinColor?.let { skinColor.toJson() },
        "IsPwdPass" to isPwdPass?.let { isPwdPass.toJson() }
    )
}
