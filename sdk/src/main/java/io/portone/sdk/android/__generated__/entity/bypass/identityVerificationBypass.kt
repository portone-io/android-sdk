package io.portone.sdk.__generated__.entity.bypass

import io.portone.sdk.__generated__.entity.bypass.identityVerification.DanalIdentityVerificationBypass
import io.portone.sdk.__generated__.entity.bypass.identityVerification.InicisUnifiedIdentityVerificationBypass
import io.portone.sdk.__generated__.entity.bypass.identityVerification.KcpV2IdentityVerificationBypass

/**
 * **PG사 본인인증 창 호출 시 PG사로 그대로 bypass할 값들의 모음**
 */
data class IdentityVerificationBypass(
    /**
     * **다날 bypass 파라미터**
     */
    val danal: DanalIdentityVerificationBypass?,
    /**
     * **KG이니시스 bypass 파라미터**
     */
    val inicisUnified: InicisUnifiedIdentityVerificationBypass?,
    /**
     * **KCP bypass 파라미터**
     */
    val kcpV2: KcpV2IdentityVerificationBypass?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "danal" to danal?.let { danal.toJson() },
        "inicisUnified" to inicisUnified?.let { inicisUnified.toJson() },
        "kcpV2" to kcpV2?.let { kcpV2.toJson() }
    )
}
