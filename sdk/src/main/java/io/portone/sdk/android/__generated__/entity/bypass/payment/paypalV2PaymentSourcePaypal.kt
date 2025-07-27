package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.payment.PaypalV2PaymentSourcePaypalExperienceContext
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaypalV2PaymentSourcePaypal(
    val experienceContext: PaypalV2PaymentSourcePaypalExperienceContext?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "experience_context" to experienceContext?.let { experienceContext.toJson() }
    )
}
