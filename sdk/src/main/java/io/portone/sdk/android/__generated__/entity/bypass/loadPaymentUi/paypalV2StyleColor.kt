package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

/**
 * 버튼 색상
 */
enum class PaypalV2StyleColor {
    gold,
    blue,
    silver,
    white,
    black;

    fun toJson(): String = name
}
