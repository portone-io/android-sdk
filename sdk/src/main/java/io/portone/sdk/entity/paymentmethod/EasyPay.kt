package io.portone.sdk.entity.paymentmethod

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
