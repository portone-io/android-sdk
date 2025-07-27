package io.portone.sdk.__generated__.entity.bypass.payment

enum class PaypalV2PaymentSourcePaypalExperienceContextPaymentMethodPreference {
    UNRESTRICTED,
    IMMEDIATE_PAYMENT_REQUIRED;

    fun toJson(): String = name
}
