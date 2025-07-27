package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayWidgetOptionsUiPromotionSection
import kotlinx.parcelize.Parcelize

@Parcelize
data class TossBrandpayWidgetOptionsUi(
    val promotionSection: TossBrandpayWidgetOptionsUiPromotionSection?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "promotionSection" to promotionSection?.let { promotionSection.toJson() }
    )
}
