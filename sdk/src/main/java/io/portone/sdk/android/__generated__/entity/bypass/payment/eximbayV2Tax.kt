package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2TaxReceiptStatus

/**
 * 세금 정보
 */
data class EximbayV2Tax(
    /**
     * 현금영수증 발급 여부
     */
    val receiptStatus: EximbayV2TaxReceiptStatus?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "receipt_status" to receiptStatus?.let { receiptStatus.toJson() }
    )
}
