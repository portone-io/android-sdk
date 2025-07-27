package io.portone.sdk.__generated__.entity.bypass.payment

enum class PaypalV2PaymentSourcePaypalExperienceContextLandingPage {
    LOGIN,
    GUEST_CHECKOUT,
    NO_PREFERENCE;

    fun toJson(): String = name
}
