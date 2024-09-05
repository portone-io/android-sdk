package io.portone.sdk.entity.paymentmethod.issuebillingkey

import android.os.Parcelable
import io.portone.sdk.entity.CardCompany
import io.portone.sdk.entity.CashReceiptType
import io.portone.sdk.entity.EasyPayPaymentMethod
import io.portone.sdk.entity.EasyPayProvider
import io.portone.sdk.entity.Installment
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class EasyPay(
    val availableCards: List<CardCompany>? = null,
): Parcelable, IssueBillingKeyMethod
