package io.portone.sdk.android.type

import android.os.Parcelable
import io.portone.sdk.android.request.type.paymentmethod.PaymentMethod as RequestPaymentMethod
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.temporal.ChronoUnit

sealed interface PaymentMethod : Parcelable {
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


    @Serializable
    @Parcelize
    data class Mobile(
        val carrier: Carrier? = null,
        val availableCarriers: List<Carrier>? = null,
    ): Parcelable, PaymentMethod

    @Serializable
    @Parcelize
    data class GiftCertificate(
        val giftCertificateType: GiftCertificateType? = null,
    ): Parcelable, PaymentMethod

    @Serializable
    @Parcelize
    data class Transfer(
        val cashReceiptType: CashReceiptType? = null,
        val customerIdentifier: String? = null,
        val bankCode: Bank? = null,
    ) : Parcelable, PaymentMethod

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

    @Parcelize
    data class VirtualAccount(
        val cashReceiptType: CashReceiptType? = null,
        val customerIdentifier: String? = null,
        val accountExpiry: AccountExpiry? = null,
        val bankCode: Bank? = null,
        val availableBanks: List<Bank>? = null,
        val fixedOption: FixedOption? = null,
    ) : PaymentMethod, Parcelable {
        sealed interface AccountExpiry : Parcelable {
            @Parcelize
            data class ValidHours(val validHours: Int) : AccountExpiry
            @Parcelize
            data class DueDate(val dueDate: Instant) :
                AccountExpiry // date is RFC 3339 date-time format
        }

        @Parcelize
        sealed interface FixedOption : Parcelable {

            @Parcelize
            data class PgAccountId(val pgAccountId : String) : FixedOption

            @Parcelize
            data class AccountNumber(val accountNumber: String) : FixedOption

        }
        internal fun toRequest(): RequestPaymentMethod.VirtualAccount {
            return RequestPaymentMethod.VirtualAccount(
                cashReceiptType = cashReceiptType,
                customerIdentifier = customerIdentifier,
                accountExpiry = when(accountExpiry){
                    is AccountExpiry.DueDate ->
                        RequestPaymentMethod.VirtualAccount.AccountExpiry(
                            dueDate = accountExpiry.dueDate.truncatedTo(ChronoUnit.SECONDS).toString()
                        )

                    is AccountExpiry.ValidHours -> RequestPaymentMethod.VirtualAccount.AccountExpiry(
                        validHours = accountExpiry.validHours
                    )
                    null -> null
                }

            )

        }
    }
}