package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

/**
 * 버튼 모양
 */
enum class PaypalV2StyleShape {
    rect,
    pill;

    fun toJson(): String = name
}
