package io.portone.sdk.android.entity.paymentmethod

import android.os.Parcelable
import io.portone.sdk.android.entity.Bank
import io.portone.sdk.android.entity.CashReceiptType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Transfer(
    val cashReceiptType: CashReceiptType? = null,
    val customerIdentifier: String? = null,
    val bankCode: Bank? = null,
) : Parcelable, PaymentMethod
