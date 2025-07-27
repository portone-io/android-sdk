package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 상점 정보
 */
data class EximbayV2Merchant(
    /**
     * 상점명
     */
    val shop: String?,
    /**
     * 파트너 코드
     */
    val partnerCode: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "shop" to shop?.let { shop },
        "partner_code" to partnerCode?.let { partnerCode }
    )
}
