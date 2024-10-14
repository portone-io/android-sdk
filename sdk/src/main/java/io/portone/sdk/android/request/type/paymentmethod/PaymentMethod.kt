package io.portone.sdk.android.request.type.paymentmethod

import io.portone.sdk.android.type.Bank
import io.portone.sdk.android.type.CashReceiptType
import kotlinx.serialization.Serializable

internal sealed interface PaymentMethod {

    @Serializable
    data class VirtualAccount(
        val cashReceiptType: CashReceiptType? = null,
        val customerIdentifier: String? = null,
        val accountExpiry: AccountExpiry? = null,
        val bankCode: Bank? = null,
        val availableBanks: List<Bank>? = null,
        val fixedOption: FixedOption? = null,
    ) : PaymentMethod {
        @Serializable
        data class AccountExpiry(
            val validHours: Int? = null,
            val dueDate: String? = null,
        )

        @Serializable
        data class FixedOption(
            val pgAccountId: String? = null,
            val accountNumber: String? = null,
        )
    }
}