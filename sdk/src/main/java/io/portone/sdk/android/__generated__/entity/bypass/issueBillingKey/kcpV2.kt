package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

import io.portone.sdk.__generated__.entity.bypass.issueBillingKey.KcpV2BatchSocChoice

/**
 * **KCP bypass 파라미터**
 */
data class KcpV2IssueBillingKeyBypass(
    /**
     * 결제창에서 주민번호/사업자 번호 고정여부 설정
     */
    val batchSocChoice: KcpV2BatchSocChoice?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "batch_soc_choice" to batchSocChoice?.let { batchSocChoice.toJson() }
    )
}
