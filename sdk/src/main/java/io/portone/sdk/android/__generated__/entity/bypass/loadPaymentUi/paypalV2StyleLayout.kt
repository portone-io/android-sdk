package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

/**
 * 버튼 렌더링 방향
 */
enum class PaypalV2StyleLayout {
    vertical,
    horizontal;

    fun toJson(): String = name
}
