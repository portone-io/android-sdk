package io.portone.sdk.android.entity.paymentmethod

import android.os.Parcelable
import io.portone.sdk.android.entity.Carrier
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Mobile(
    val carrier: Carrier? = null,
    val availableCarriers: List<Carrier>? = null,
): Parcelable, PaymentMethod
