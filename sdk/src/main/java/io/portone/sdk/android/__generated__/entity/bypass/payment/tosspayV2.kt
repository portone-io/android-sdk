package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.TosspayV2CashReceiptTradeOption

/**
 * 토스페이 bypass 파라미터
 */
data class TosspayV2PaymentBypass(
    /**
     * 결제 만료 기한 (yyyy-MM-dd HH:mm:ss)
     */
    val expiredTime: String?,
    /**
     * 현금영수증 발급타입
     * 
     * - CULTURE: 문화비
     * - GENERAL: 일반 (기본값)
     * - PUBLIC\_TP: 교통비
     */
    val cashReceiptTradeOption: TosspayV2CashReceiptTradeOption?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "expiredTime" to expiredTime?.let { expiredTime },
        "cashReceiptTradeOption" to cashReceiptTradeOption?.let { cashReceiptTradeOption.toJson() }
    )
}
