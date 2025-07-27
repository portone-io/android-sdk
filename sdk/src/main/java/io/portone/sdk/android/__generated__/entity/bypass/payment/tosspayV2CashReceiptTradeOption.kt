package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 현금영수증 발급타입
 * 
 * - CULTURE: 문화비
 * - GENERAL: 일반 (기본값)
 * - PUBLIC\_TP: 교통비
 */
enum class TosspayV2CashReceiptTradeOption {
    /**
     * 문화비
     */
    CULTURE,
    /**
     * 일반 (기본값)
     */
    GENERAL,
    /**
     * 교통비
     */
    PUBLIC_TP;

    fun toJson(): String = name
}
