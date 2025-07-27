package io.portone.sdk.__generated__.request

import io.portone.sdk.__generated__.entity.GiftCertificateType

/**
 * **상품권 결제 설정**
 */
data class PaymentRequestUnionGiftCertificate(
    /**
     * **상품권 종류**
     */
    val giftCertificateType: GiftCertificateType?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "giftCertificateType" to giftCertificateType?.let { giftCertificateType.toJson() }
    )
}
