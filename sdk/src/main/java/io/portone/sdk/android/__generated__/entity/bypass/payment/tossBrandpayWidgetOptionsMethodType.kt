package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 위젯에 보여줄 결제 수단. 예) 카드 전달시 등록한 결제 수단 중 카드만 노출 됨
 */
enum class TossBrandpayWidgetOptionsMethodType {
    CARD,
    ACCOUNT;

    fun toJson(): String = name
}
