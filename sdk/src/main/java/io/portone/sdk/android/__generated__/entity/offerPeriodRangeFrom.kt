package io.portone.sdk.__generated__.entity

import io.portone.sdk.__generated__.entity.OfferPeriodRange

/**
 * **시작 시점만 있는 기간 범위**
 */
data class OfferPeriodRangeFrom(
    /**
     * **시작 시점**
     */
    val from: String
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "from" to from
    )
}
