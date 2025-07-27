package io.portone.sdk.__generated__.request

/**
 * **가상계좌 입금 만료 기한**
 * 
 * 토스페이먼츠, KG이니시스, NHN KCP에서 지원합니다.
 * 
 * `validHours`와 `dueDate` 중 하나만 지정합니다.
 */
sealed interface PaymentRequestUnionVirtualAccountAccountExpiry {
    /**
     * **유효 시간**
     * 
     * 예) 3을 전달하면 지금으로부터 3시간 후가 만료 기한으로 지정 됨
     */
    data class ValidHours(val value: Long) : PaymentRequestUnionVirtualAccountAccountExpiry
    /**
     * **만료 시각**
     * 
     * - YYYYMMDD
     * - YYYYMMDDHHmmss
     * - YYYY-MM-DD
     * - YYYY-MM-DD HH:mm:ss
     */
    data class DueDate(val value: String) : PaymentRequestUnionVirtualAccountAccountExpiry

    fun toJson(): Map<String, Any> = when (this) {
        is ValidHours -> mapOf("validHours" to value)
        is DueDate -> mapOf("dueDate" to value)
    }
}
