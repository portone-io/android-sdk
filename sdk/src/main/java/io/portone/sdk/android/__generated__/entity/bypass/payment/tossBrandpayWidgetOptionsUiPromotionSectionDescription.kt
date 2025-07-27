package io.portone.sdk.__generated__.entity.bypass.payment

data class TossBrandpayWidgetOptionsUiPromotionSectionDescription(
    /**
     * 결제 혜택 영역을 보여줄지 여부. 기본값은 true
     */
    val visible: Boolean?,
    /**
     * 결제 혜택의 상세 설명을 보여줄지 여부. 각 카드사의 결제 혜택을 자세히 설명 함. 기본값은 false
     */
    val defaultOpen: Boolean?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "visible" to visible?.let { visible },
        "defaultOpen" to defaultOpen?.let { defaultOpen }
    )
}
