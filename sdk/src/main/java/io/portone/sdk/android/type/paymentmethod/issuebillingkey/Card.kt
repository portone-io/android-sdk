package io.portone.sdk.android.type.paymentmethod.issuebillingkey

import android.os.Parcelable
import io.portone.sdk.entity.CardCompany
import io.portone.sdk.entity.paymentmethod.issuebillingkey.IssueBillingKeyMethod
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Card(
    val cardCompany: CardCompany? = null,
) : IssueBillingKeyMethod, Parcelable
