package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayBrandpayOptionsUi

/**
 * loadBrandpay 호출시 전달하는 세번째 파라미터
 */
data class TossBrandpayBrandpayOptions(
    val ui: TossBrandpayBrandpayOptionsUi?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "ui" to ui?.let { ui.toJson() }
    )
}
