package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.KpnCardSelect

/**
 * KPN bypass 파라미터
 */
data class KpnBypass(
    /**
     * - 해외카드 (VISA + MASTER + JCB) : `GLOBAL`
     * - 11Pay (SKPay) : `11PAY`
     * - 구인증 : `LEGACY_AUTH`
     * - 키인 : `KEY_IN`
     */
    val cardSelect: List<KpnCardSelect>?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "CardSelect" to cardSelect?.let { cardSelect.map { it.toJson() } }
    )
}
