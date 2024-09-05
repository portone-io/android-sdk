package io.portone.sdk.entity.paymentmethod.issuebillingkey

import android.os.Parcelable
import io.portone.sdk.entity.CardCompany
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Card(
    val cardCompany: CardCompany? = null,
) : IssueBillingKeyMethod, Parcelable
