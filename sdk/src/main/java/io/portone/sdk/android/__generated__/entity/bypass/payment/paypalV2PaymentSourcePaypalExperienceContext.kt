package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.payment.PaypalV2PaymentSourcePaypalExperienceContextLandingPage
import io.portone.sdk.__generated__.entity.bypass.payment.PaypalV2PaymentSourcePaypalExperienceContextPaymentMethodPreference
import io.portone.sdk.__generated__.entity.bypass.payment.PaypalV2PaymentSourcePaypalExperienceContextShippingPreference
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaypalV2PaymentSourcePaypalExperienceContext(
    val brandName: String?,
    val shippingPreference: PaypalV2PaymentSourcePaypalExperienceContextShippingPreference?,
    val landingPage: PaypalV2PaymentSourcePaypalExperienceContextLandingPage?,
    val paymentMethodPreference: PaypalV2PaymentSourcePaypalExperienceContextPaymentMethodPreference?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "brand_name" to brandName?.let { brandName },
        "shipping_preference" to shippingPreference?.let { shippingPreference.toJson() },
        "landing_page" to landingPage?.let { landingPage.toJson() },
        "payment_method_preference" to paymentMethodPreference?.let { paymentMethodPreference.toJson() }
    )
}
