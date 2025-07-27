package io.portone.sdk.__generated__.entity

import io.portone.sdk.__generated__.entity.OfferPeriodRangeFrom
import io.portone.sdk.__generated__.entity.OfferPeriodRangeFromTo
import io.portone.sdk.__generated__.entity.OfferPeriodRangeTo

/**
 * **기간 범위**
 */
sealed class OfferPeriodRange {
    data class OfferPeriodRangeFrom(val value: OfferPeriodRangeFrom) : OfferPeriodRange()
    data class OfferPeriodRangeTo(val value: OfferPeriodRangeTo) : OfferPeriodRange()
    data class OfferPeriodRangeFromTo(val value: OfferPeriodRangeFromTo) : OfferPeriodRange()

    fun toJson(): Any = when (this) {
        is OfferPeriodRangeFrom -> value.toJson()
        is OfferPeriodRangeTo -> value.toJson()
        is OfferPeriodRangeFromTo -> value.toJson()
    }
}
