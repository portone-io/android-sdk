package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.SmartroV2OpenType
import io.portone.sdk.__generated__.entity.bypass.payment.SmartroV2SkinColor

/**
 * 스마트로 V2 bypass 파라미터
 */
data class SmartroV2PaymentBypass(
    /**
     * 결제 상품 품목 개수
     */
    val goodsCnt: Long?,
    /**
     * UI 스타일 (기본값: `"RED"`)
     * 
     * `"RED"`, `"GREEN"`, `"BLUE"`, `"PURPLE"` 중 하나의 값으로 입력해주세요.
     */
    val skinColor: SmartroV2SkinColor?,
    /**
     * 해외 카드만 결제를 허용할지 여부(기본값: `"KR"`)
     * 
     * `"KR"`, `"EN"` 중 하나의 값으로 입력해주세요.
     */
    val openType: SmartroV2OpenType?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "GoodsCnt" to goodsCnt?.let { goodsCnt },
        "SkinColor" to skinColor?.let { skinColor.toJson() },
        "OpenType" to openType?.let { openType.toJson() }
    )
}
