package io.portone.sdk.android.request.type.paymentmethod

import android.os.Parcelable
import io.portone.sdk.android.type.Bank
import io.portone.sdk.android.type.CashReceiptType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

internal sealed interface PaymentMethod {

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
        data class AccountExpiry(
            val validHours: Int? = null,
            val dueDate: String? = null,
        ) : Parcelable

        @Serializable
        @Parcelize
        data class FixedOption(
            val pgAccountId: String? = null,
            val accountNumber: String? = null,
        ) : Parcelable
    }
}