package io.portone.sdk.android.entity.paymentmethod

import android.os.Parcelable
import io.portone.sdk.android.entity.CardCompany
import io.portone.sdk.android.entity.CashReceiptType
import io.portone.sdk.android.entity.EasyPayPaymentMethod
import io.portone.sdk.android.entity.EasyPayProvider
import io.portone.sdk.android.entity.Installment
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class EasyPay(
    val easyPayProvider: EasyPayProvider? = null,
    val installment: Installment? = null,
    val availableCards: List<CardCompany>? = null,
    val useFreeInterestFromMall: Boolean? = null,
    val cashReceiptType: CashReceiptType? = null,
    val customerIdentifier: String? = null,
    val useCardPoint: Boolean? = null,
    val availablePayMethods: List<EasyPayPaymentMethod>? = null,
    val useInstallment: Boolean? = null,
): Parcelable, PaymentMethod
