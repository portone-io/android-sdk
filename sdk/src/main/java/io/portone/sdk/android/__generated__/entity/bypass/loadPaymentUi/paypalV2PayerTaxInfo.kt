package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

/**
 * 구매자 정보
 */
data class PaypalV2PayerTaxInfo(
    /**
     * 구매자 세금 정보 (브라질 구매자의 경우 필수 입력)
     */
    val taxId: String,
    val taxIdType: String
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "tax_id" to taxId,
        "tax_id_type" to taxIdType
    )
}
