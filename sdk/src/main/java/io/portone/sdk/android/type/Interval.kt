package io.portone.sdk.android.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
sealed interface Interval : Parcelable {

    @Serializable
    data class Day(val day: Int) : Interval

    @Serializable
    data class Month(val month: Int) : Interval

    @Serializable
    data class Year(val year: Int) : Interval
}
