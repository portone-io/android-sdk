package io.portone.sdk.entity.paymentmethod

import android.os.Parcelable
import io.portone.sdk.entity.Bank
import io.portone.sdk.entity.CashReceiptType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Transfer(
    val cashReceiptType: CashReceiptType? = null,
    val customerIdentifier: String? = null,
    val bankCode: Bank? = null,
) : Parcelable, PaymentMethod
