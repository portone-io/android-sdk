package io.portone.sdk.entity.paymentmethod

import android.os.Parcelable
import io.portone.sdk.entity.CardCompany
import io.portone.sdk.entity.Installment
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Card(
    val cardCompany: CardCompany? = null,
    val installment: Installment? = null,
    val useCardPoint: Boolean? = null,
    val useAppCardOnly: Boolean? = null,
    val availableCards: List<CardCompany>? = null,
    val useFreeInterestFromMall: Boolean? = null,
    val useInstallment: Boolean? = null,
) : PaymentMethod, Parcelable
