package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayWidgetOptionsUiPromotionSectionDescription
import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayWidgetOptionsUiPromotionSectionSummary
import kotlinx.parcelize.Parcelize

@Parcelize
data class TossBrandpayWidgetOptionsUiPromotionSection(
    val summary: TossBrandpayWidgetOptionsUiPromotionSectionSummary?,
    val description: TossBrandpayWidgetOptionsUiPromotionSectionDescription?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "summary" to summary?.let { summary.toJson() },
        "description" to description?.let { description.toJson() }
    )
}
