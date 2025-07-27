package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.InicisJpPaymentUI

/**
 * 이니시스 일본 bypass 파라미터
 */
data class InicisJpBypass(
    /**
     * 결제창 UI 설정
     */
    val paymentUi: InicisJpPaymentUI?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "paymentUI" to paymentUi?.let { paymentUi.toJson() }
    )
}
