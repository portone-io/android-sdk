package io.portone.sdk.android.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
sealed interface BillingKeyMethod : Parcelable {
    @Serializable
    @Parcelize
    data class Card(
        val cardCompany: CardCompany? = null,
    ) : BillingKeyMethod, Parcelable

    @Serializable
    @Parcelize
    data class EasyPay(
        val availableCards: List<CardCompany>? = null,
    ) : Parcelable, BillingKeyMethod

    @Serializable
    @Parcelize
    data class Mobile(
        val carrier: Carrier? = null,
        val availableCarriers: List<Carrier>? = null,
    ) : Parcelable, BillingKeyMethod

    @Parcelize
    object Paypal: BillingKeyMethod, Parcelable
}