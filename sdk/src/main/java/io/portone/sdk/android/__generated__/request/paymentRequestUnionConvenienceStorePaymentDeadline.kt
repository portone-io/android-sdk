package io.portone.sdk.__generated__.request

/**
 * **편의점결제 지불기한**
 */
sealed interface PaymentRequestUnionConvenienceStorePaymentDeadline {
    /**
     * **유효 시간 (단위: 시간)**
     */
    data class ValidHours(val value: Long) : PaymentRequestUnionConvenienceStorePaymentDeadline
    /**
     * **만료일시**
     * 
     * RFC 3339 형식입니다.
     */
    data class DueDate(val value: String) : PaymentRequestUnionConvenienceStorePaymentDeadline

    fun toJson(): Map<String, Any> = when (this) {
        is ValidHours -> mapOf("validHours" to value)
        is DueDate -> mapOf("dueDate" to value)
    }
}
