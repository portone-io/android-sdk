package io.portone.sdk.entity.paymentmethod.issuebillingkey

import android.os.Parcelable
import io.portone.sdk.entity.Carrier
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Mobile(
    val carrier: Carrier? = null,
    val availableCarriers: List<Carrier>? = null,
): Parcelable, IssueBillingKeyMethod
