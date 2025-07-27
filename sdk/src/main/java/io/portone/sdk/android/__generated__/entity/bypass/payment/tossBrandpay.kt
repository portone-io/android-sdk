package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayBrandpayOptions
import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayWidgetOptions

/**
 * 토스 브랜드페이 bypass 파라미터
 */
data class TossBrandpayPaymentBypass(
    /**
     * loadBrandpay 호출시 전달하는 세번째 파라미터
     */
    val brandpayOptions: TossBrandpayBrandpayOptions?,
    /**
     * 브랜드페이 위젯 render() 함수 호출시 전달하는 두번째 파라미터
     */
    val widgetOptions: TossBrandpayWidgetOptions?,
    /**
     * 카드사 할인코드
     */
    val discountCode: String?,
    /**
     * 등록되어 있는 결제수단 중 하나를 지정해서 바로 결제하고 싶을 때 사용
     */
    val methodId: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "brandpayOptions" to brandpayOptions?.let { brandpayOptions.toJson() },
        "widgetOptions" to widgetOptions?.let { widgetOptions.toJson() },
        "discountCode" to discountCode?.let { discountCode },
        "methodId" to methodId?.let { methodId }
    )
}
