package io.portone.sdk.android.request.type

import kotlinx.serialization.Serializable

@Serializable
internal data class OfferPeriod(
    val range: Range?,
    val interval: String?
) {
    @Serializable
    data class Range(
        val from: String?,
        val to: String?
    )
}