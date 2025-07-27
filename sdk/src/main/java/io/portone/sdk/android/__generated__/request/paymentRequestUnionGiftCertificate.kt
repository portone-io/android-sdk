package io.portone.sdk.__generated__.request

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.GiftCertificateType
import kotlinx.parcelize.Parcelize

/**
 * **상품권 결제 설정**
 */
@Parcelize
data class PaymentRequestUnionGiftCertificate(
    /**
     * **상품권 종류**
     */
    val giftCertificateType: GiftCertificateType?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "giftCertificateType" to giftCertificateType?.let { giftCertificateType.toJson() }
    )
}
