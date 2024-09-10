package io.portone.sdk.android.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class OfferPeriod(
    val range: Range?,
    val interval: Interval?
) : Parcelable {

    @Serializable
    @Parcelize
    data class Range(
        val from: String?,
        val to: String?
    ) : Parcelable

    @Serializable
    @Parcelize
    sealed interface Interval : Parcelable {
        val value: Int

        @Serializable
        data class DAY(override val value: Int) : Interval

        @Serializable
        data class MONTH(override val value: Int) : Interval

        @Serializable
        data class YEAR(override val value: Int) : Interval
    }
}
