package io.portone.sdk.android.request.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class OfferPeriod(
    val range: Range?,
    val interval: String?
) : Parcelable{
    @Serializable
    @Parcelize
    data class Range(
        val from: String?,
        val to: String?
    ) : Parcelable
}