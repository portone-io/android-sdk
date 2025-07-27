package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

import io.portone.sdk.__generated__.entity.bypass.issueBillingKey.InicisV2CardUse

/**
 * **KG이니시스 bypass 파라미터**
 */
data class InicisV2IssueBillingKeyBypass(
    /**
     * 개인/법인카드 선택 옵션
     */
    val carduse: InicisV2CardUse?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "carduse" to carduse?.let { carduse.toJson() }
    )
}
