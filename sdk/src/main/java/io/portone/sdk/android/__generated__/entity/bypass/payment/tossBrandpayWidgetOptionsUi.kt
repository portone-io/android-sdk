package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayWidgetOptionsUiPromotionSection

data class TossBrandpayWidgetOptionsUi(
    val promotionSection: TossBrandpayWidgetOptionsUiPromotionSection?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "promotionSection" to promotionSection?.let { promotionSection.toJson() }
    )
}
