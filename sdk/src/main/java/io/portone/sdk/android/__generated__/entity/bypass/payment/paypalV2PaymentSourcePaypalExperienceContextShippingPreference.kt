package io.portone.sdk.__generated__.entity.bypass.payment

enum class PaypalV2PaymentSourcePaypalExperienceContextShippingPreference {
    GET_FROM_FILE,
    NO_SHIPPING,
    SET_PROVIDED_ADDRESS;

    fun toJson(): String = name
}
