package io.portone.sdk.__generated__.entity

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.OfferPeriodRangeFrom
import io.portone.sdk.__generated__.entity.OfferPeriodRangeFromTo
import io.portone.sdk.__generated__.entity.OfferPeriodRangeTo
import kotlinx.parcelize.Parcelize

/**
 * **기간 범위**
 */
@Parcelize
sealed class OfferPeriodRange : Parcelable {
    @Parcelize
    data class OfferPeriodRangeFrom(val value: OfferPeriodRangeFrom) : OfferPeriodRange()
    @Parcelize
    data class OfferPeriodRangeTo(val value: OfferPeriodRangeTo) : OfferPeriodRange()
    @Parcelize
    data class OfferPeriodRangeFromTo(val value: OfferPeriodRangeFromTo) : OfferPeriodRange()

    fun toJson(): Any = when (this) {
        is OfferPeriodRangeFrom -> value.toJson()
        is OfferPeriodRangeTo -> value.toJson()
        is OfferPeriodRangeFromTo -> value.toJson()
    }
}
