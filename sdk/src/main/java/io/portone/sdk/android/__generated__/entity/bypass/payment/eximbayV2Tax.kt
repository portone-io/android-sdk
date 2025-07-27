package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2TaxReceiptStatus
import kotlinx.parcelize.Parcelize

/**
 * 세금 정보
 */
@Parcelize
data class EximbayV2Tax(
    /**
     * 현금영수증 발급 여부
     */
    val receiptStatus: EximbayV2TaxReceiptStatus?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "receipt_status" to receiptStatus?.let { receiptStatus.toJson() }
    )
}
