package io.portone.sdk.__generated__.response

/**
 * **리디렉션 없이 결제 UI가 표시된 경우 반환값**
 */
data class IdentityVerificationResponse(
    /**
     * **트랜잭션 유형**
     * 
     * 본인인증의 경우 경우 항상 `IDENTITY_VERIFICATION`으로 전달됩니다.
     */
    val transactionType: String,
    /**
     * **본인인증 ID**
     * 
     * 본인인증 ID입니다.
     */
    val identityVerificationId: String,
    /**
     * **본인인증 시도 ID**
     * 
     * 요청마다 고유하게 생성되는 본인인증 시도 ID입니다.
     */
    val identityVerificationTxId: String,
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
        "identityVerificationId" to identityVerificationId,
        "identityVerificationTxId" to identityVerificationTxId,
        "code" to code?.let { code },
        "message" to message?.let { message },
        "pgCode" to pgCode?.let { pgCode },
        "pgMessage" to pgMessage?.let { pgMessage }
    )
}
