package io.portone.sdk.__generated__.entity

/**
 * 빌링키 발급 및 초회결제 수단
 */
enum class BillingKeyAndPayMethod {
    /**
     * 휴대전화
     */
    MOBILE;

    fun toJson(): String = name
}
