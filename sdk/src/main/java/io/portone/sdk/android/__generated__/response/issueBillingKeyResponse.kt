package io.portone.sdk.__generated__.response

/**
 * **리디렉션 없이 빌링키 발급 UI가 표시된 경우 반환값**
 */
data class IssueBillingKeyResponse(
    /**
     * `ISSUE_BILLING_KEY`
     */
    val transactionType: String,
    /**
     * **빌링키**
     * 
     * 빌링 결제를 일으킬 때 사용하는 빌링키입니다.
     */
    val billingKey: String,
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
        "billingKey" to billingKey,
        "code" to code?.let { code },
        "message" to message?.let { message },
        "pgCode" to pgCode?.let { pgCode },
        "pgMessage" to pgMessage?.let { pgMessage }
    )
}
