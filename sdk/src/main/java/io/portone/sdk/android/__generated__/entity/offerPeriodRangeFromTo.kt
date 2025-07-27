package io.portone.sdk.__generated__.entity

import io.portone.sdk.__generated__.entity.OfferPeriodRange

/**
 * **시작 지점과 종료 지점이 모두 있는 기간 범위**
 */
data class OfferPeriodRangeFromTo(
    /**
     * **시작 시점**
     */
    val from: String,
    /**
     * **종료 지점**
     */
    val to: String
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "from" to from,
        "to" to to
    )
}
