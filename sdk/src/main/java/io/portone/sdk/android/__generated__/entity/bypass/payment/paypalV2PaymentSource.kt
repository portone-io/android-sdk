package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.payment.PaypalV2PaymentSourcePaypal
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaypalV2PaymentSource(
    val paypal: PaypalV2PaymentSourcePaypal?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "paypal" to paypal?.let { paypal.toJson() }
    )
}
