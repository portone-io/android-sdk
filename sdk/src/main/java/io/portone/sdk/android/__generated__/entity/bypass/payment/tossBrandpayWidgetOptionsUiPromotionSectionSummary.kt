package io.portone.sdk.__generated__.entity.bypass.payment

data class TossBrandpayWidgetOptionsUiPromotionSectionSummary(
    /**
     * 혜택 배지 영역을 보여줄지 여부. 혜택 배지 영역에서는 즉시 할인 대상 카드 정보 등을 간략히 보여 줌. 기본값은 true
     */
    val visible: Boolean?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "visible" to visible?.let { visible }
    )
}
