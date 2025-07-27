package io.portone.sdk.__generated__.entity

/**
 * 빌링키 발급 수단
 */
enum class BillingKeyMethod {
    /**
     * 카드
     */
    CARD,
    /**
     * 휴대전화
     */
    MOBILE,
    /**
     * 간편결제
     */
    EASY_PAY,
    /**
     * 페이팔(RT)
     */
    PAYPAL;

    fun toJson(): String = name
}
