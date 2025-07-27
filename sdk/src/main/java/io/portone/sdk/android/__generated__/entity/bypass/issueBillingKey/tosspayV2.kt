package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

/**
 * **토스페이 bypass 파라미터**
 */
data class TosspayV2IssueBillingKeyBypass(
    /**
     * 암호화된 사용자 CI
     */
    val encryptedUserCi: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "encryptedUserCi" to encryptedUserCi?.let { encryptedUserCi }
    )
}
