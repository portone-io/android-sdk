package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

/**
 * 버튼 라벨
 */
enum class PaypalV2StyleLabel {
    paypal,
    checkout,
    buynow,
    pay,
    installment,
    subscribe,
    donate;

    fun toJson(): String = name
}
