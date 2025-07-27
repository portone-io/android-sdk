package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 결제 정보
 */
data class EximbayV2Payment(
    /**
     * 결제수단 단독 노출
     */
    val paymentMethod: String?,
    /**
     * 결제수단 노출 목록
     */
    val multiPaymentMethod: List<String>?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "payment_method" to paymentMethod?.let { paymentMethod },
        "multi_payment_method" to multiPaymentMethod?.let { multiPaymentMethod }
    )
}
