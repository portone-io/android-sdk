package io.portone.sdk.entity.paymentmethod

import android.os.Parcelable
import io.portone.sdk.entity.Bank
import io.portone.sdk.entity.CashReceiptType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class VirtualAccount(
    val cashReceiptType: CashReceiptType? = null,
    val customerIdentifier: String? = null,
    val accountExpiry: AccountExpiry? = null,
    val bankCode: Bank? = null,
    val availableBanks: List<Bank>? = null,
    val fixedOption: FixedOption? = null,
) : PaymentMethod, Parcelable {
    @Serializable
    @Parcelize
    sealed interface AccountExpiry : Parcelable {
        @Serializable
        @Parcelize
        data class ValidHours(val validHours: Int) : AccountExpiry
        @Serializable
        @Parcelize
        data class DueDate(val dueDate: String) : AccountExpiry // date is RFC 3339 date-time format
    }

    @Serializable
    @Parcelize
    sealed interface FixedOption : Parcelable {

        @Serializable
        @Parcelize
        data class PgAccountId(val value: String) : FixedOption

        @Serializable
        @Parcelize
        data class AccountNumber(val value: String) : FixedOption

    }
}
