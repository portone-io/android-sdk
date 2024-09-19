package io.portone.sdk.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class OfferPeriod(
    val range: Range? = null,
    val interval: String? = null,
) : Parcelable {

    @Serializable
    @Parcelize
    data class Range(
        val from: String? = null,
        val to: String? = null,
    ) : Parcelable
}
