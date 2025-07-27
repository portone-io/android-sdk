package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.PaypalV2PaymentSourcePaypalExperienceContext

data class PaypalV2PaymentSourcePaypal(
    val experienceContext: PaypalV2PaymentSourcePaypalExperienceContext?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "experience_context" to experienceContext?.let { experienceContext.toJson() }
    )
}
