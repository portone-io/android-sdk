package io.portone.sdk.android.type

import android.os.Parcelable
import io.portone.sdk.android.request.type.OfferPeriod
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.temporal.ChronoUnit

@Serializable
@Parcelize
data class OfferPeriod(
    val range: Range?,
    val interval: Interval?
) : Parcelable {

    @Serializable
    @Parcelize
    data class Range(
        @Contextual val from: Instant?,
        @Contextual val to: Instant?
    ) : Parcelable

    internal fun toRequest(): OfferPeriod {
        return OfferPeriod(
            range = OfferPeriod.Range(
                from = range?.from?.truncatedTo(ChronoUnit.SECONDS).toString(),
                to = range?.to?.truncatedTo(ChronoUnit.SECONDS).toString(),
            ),
            interval = when (interval) {
                is Interval.Day -> "${interval.day}d"
                is Interval.Month -> "${interval.month}m"
                is Interval.Year -> "${interval.year}y"
                null -> null
            }
        )
    }
}
