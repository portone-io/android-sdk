package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

import io.portone.sdk.__generated__.entity.bypass.issueBillingKey.SmartroV2IsPwdPass
import io.portone.sdk.__generated__.entity.bypass.issueBillingKey.SmartroV2SkinColor

/**
 * **스마트로 bypass 파라미터**
 */
data class SmartroV2IssueBillingKeyBypass(
    /**
     * UI 스타일(기본: RED)
     */
    val skinColor: SmartroV2SkinColor?,
    /**
     * 결제 비밀번호 등록 Skip 여부
     */
    val isPwdPass: SmartroV2IsPwdPass?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "SkinColor" to skinColor?.let { skinColor.toJson() },
        "IsPwdPass" to isPwdPass?.let { isPwdPass.toJson() }
    )
}
