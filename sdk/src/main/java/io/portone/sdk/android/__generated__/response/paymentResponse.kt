package io.portone.sdk.__generated__.response

/**
 * **리디렉션 없이 결제 UI가 표시된 경우 반환값**
 */
data class PaymentResponse(
    /**
     * **유형**
     * 
     * 일반결제의 경우 무조건 `PAYMENT`로 전달됩니다.
     */
    val transactionType: String,
    /**
     * **결제 시도 ID**
     * 
     * 요청마다 고유하게 생성되는 결제 시도 ID입니다.
     */
    val txId: String,
    /**
     * **결제 ID**
     */
    val paymentId: String,
    /**
     * **오류 코드**
     * 
     * 실패한 경우 오류 코드입니다.
     */
    val code: String?,
    /**
     * **오류 메시지**
     * 
     * 실패한 경우 오류 메시지입니다.
     */
    val message: String?,
    /**
     * **PG 오류 코드**
     * 
     * PG에서 오류 코드를 내려 주는 경우 이 오류 코드를 그대로 반환합니다.
     */
    val pgCode: String?,
    /**
     * **PG 오류 메시지**
     * 
     * PG에서 오류 메시지를 내려 주는 경우 이 오류 메시지를 그대로 반환합니다.
     */
    val pgMessage: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "transactionType" to transactionType,
        "txId" to txId,
        "paymentId" to paymentId,
        "code" to code?.let { code },
        "message" to message?.let { message },
        "pgCode" to pgCode?.let { pgCode },
        "pgMessage" to pgMessage?.let { pgMessage }
    )
}
