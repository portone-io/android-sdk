package io.portone.sdk.__generated__.entity

import io.portone.sdk.__generated__.entity.OfferPeriodRange

/**
 * **종료 지점만 있는 기간 범위**
 */
data class OfferPeriodRangeTo(
    /**
     * **종료 지점**
     */
    val to: String
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "to" to to
    )
}
