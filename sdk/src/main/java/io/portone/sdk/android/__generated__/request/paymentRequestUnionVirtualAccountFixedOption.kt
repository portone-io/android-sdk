package io.portone.sdk.__generated__.request

/**
 * **고정식 가상계좌 설정**
 */
sealed interface PaymentRequestUnionVirtualAccountFixedOption {
    /**
     * PG사로부터 사전에 가상계좌에 대한 ID를 발급받아 사용하는 경우의 가상계좌 ID
     */
    data class PgAccountId(val value: String) : PaymentRequestUnionVirtualAccountFixedOption
    /**
     * 고정식으로 사용할 가상계좌 번호
     */
    data class AccountNumber(val value: String) : PaymentRequestUnionVirtualAccountFixedOption

    fun toJson(): Map<String, Any> = when (this) {
        is PgAccountId -> mapOf("pgAccountId" to value)
        is AccountNumber -> mapOf("accountNumber" to value)
    }
}
