package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayWidgetOptionsUiPromotionSectionDescription
import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayWidgetOptionsUiPromotionSectionSummary

data class TossBrandpayWidgetOptionsUiPromotionSection(
    val summary: TossBrandpayWidgetOptionsUiPromotionSectionSummary?,
    val description: TossBrandpayWidgetOptionsUiPromotionSectionDescription?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "summary" to summary?.let { summary.toJson() },
        "description" to description?.let { description.toJson() }
    )
}
