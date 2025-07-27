package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 카카오페이 bypass 파라미터
 */
data class KakaopayPaymentBypass(
    /**
     * 카카오페이 결제창에 띄워줄 사용자 정의 문구
     */
    val customMessage: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "custom_message" to customMessage?.let { customMessage }
    )
}
