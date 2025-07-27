package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

/**
 * 결제창에서 주민번호/사업자 번호 고정여부 설정
 */
enum class KcpV2BatchSocChoice {
    /**
     * 주민번호만 표시
     */
    S,
    /**
     * 사업자번호만 표시
     */
    C;

    fun toJson(): String = name
}
