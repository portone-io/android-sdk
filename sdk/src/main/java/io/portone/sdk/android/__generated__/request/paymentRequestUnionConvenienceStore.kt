package io.portone.sdk.__generated__.request

import android.os.Parcelable
import io.portone.sdk.__generated__.request.PaymentRequestUnionConvenienceStorePaymentDeadline
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentRequestUnionConvenienceStore(
    /**
     * **편의점결제 지불기한**
     */
    val paymentDeadline: PaymentRequestUnionConvenienceStorePaymentDeadline?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "paymentDeadline" to paymentDeadline?.let { paymentDeadline.toJson() }
    )
}
