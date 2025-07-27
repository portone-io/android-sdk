package io.portone.sdk.__generated__.entity

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.OfferPeriodRange
import kotlinx.parcelize.Parcelize

/**
 * **종료 지점만 있는 기간 범위**
 */
@Parcelize
data class OfferPeriodRangeTo(
    /**
     * **종료 지점**
     */
    val to: String
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "to" to to
    )
}
