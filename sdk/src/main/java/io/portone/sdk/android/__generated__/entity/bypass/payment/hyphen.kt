package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 하이픈 bypass 파라미터
 */
data class HyphenBypass(
    val designCd: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "designCd" to designCd?.let { designCd }
    )
}
