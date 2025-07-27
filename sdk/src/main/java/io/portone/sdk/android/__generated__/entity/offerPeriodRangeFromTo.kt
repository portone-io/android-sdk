package io.portone.sdk.__generated__.entity

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.OfferPeriodRange
import kotlinx.parcelize.Parcelize

/**
 * **시작 지점과 종료 지점이 모두 있는 기간 범위**
 */
@Parcelize
data class OfferPeriodRangeFromTo(
    /**
     * **시작 시점**
     */
    val from: String,
    /**
     * **종료 지점**
     */
    val to: String
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "from" to from,
        "to" to to
    )
}
