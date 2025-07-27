package io.portone.sdk.__generated__.entity

/**
 * 간편결제 결제수단
 */
enum class EasyPayPaymentMethod {
    /**
     * 카드
     */
    CARD,
    /**
     * 포인트(충전, 적립) 결제
     */
    CHARGE,
    /**
     * 계좌결제
     */
    TRANSFER,
    /**
     * 포인트 및 계좌결제가 모두 가능한 경우
     */
    MONEY;

    fun toJson(): String = name
}
