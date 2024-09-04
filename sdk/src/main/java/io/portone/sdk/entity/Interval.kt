package io.portone.sdk.entity

interface Interval {
    val value: Int
    data class DAY(override val value: Int): Interval
    data class MONTH(override val value: Int): Interval
    data class YEAR(override val value: Int): Interval
}
