package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 상점 정보
 */
@Parcelize
data class EximbayV2Merchant(
    /**
     * 상점명
     */
    val shop: String?,
    /**
     * 파트너 코드
     */
    val partnerCode: String?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "shop" to shop?.let { shop },
        "partner_code" to partnerCode?.let { partnerCode }
    )
}
