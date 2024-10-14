package io.portone.sdk.android.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
sealed interface BillingKeyAndPayMethod: Parcelable {
    @Serializable
    @Parcelize
    data class Mobile(
        val carrier: Carrier? = null,
        val availableCarriers: List<Carrier>? = null,
    ) : Parcelable, BillingKeyAndPayMethod
}