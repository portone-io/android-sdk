package io.portone.sdk.__generated__.request

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.Carrier
import kotlinx.parcelize.Parcelize

@Parcelize
data class IssueBillingKeyRequestUnionMobile(
    /**
     * 통신사 코드
     */
    val carrier: Carrier?,
    val avaliableCarriers: List<Carrier>?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "carrier" to carrier?.let { carrier.toJson() },
        "avaliableCarriers" to avaliableCarriers?.let { avaliableCarriers.map { it.toJson() } }
    )
}
