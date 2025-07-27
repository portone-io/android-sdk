package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayWidgetOptionsMethodType
import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayWidgetOptionsUi

/**
 * 브랜드페이 위젯 render() 함수 호출시 전달하는 두번째 파라미터
 */
data class TossBrandpayWidgetOptions(
    /**
     * 위젯에 보여줄 결제 수단. 예) 카드 전달시 등록한 결제 수단 중 카드만 노출 됨
     */
    val methodType: TossBrandpayWidgetOptionsMethodType?,
    /**
     * 위젯에서 기본 결제 수단으로 선택할 결제 수단의 ID
     */
    val methodId: String?,
    val ui: TossBrandpayWidgetOptionsUi?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "methodType" to methodType?.let { methodType.toJson() },
        "methodId" to methodId?.let { methodId },
        "ui" to ui?.let { ui.toJson() }
    )
}
