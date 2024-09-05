package io.portone.sdk

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
sealed interface IssueBillingKeyResponse : Parcelable {
    @Parcelize
    data class Fail(
        val transactionType: TransactionType?,
        val billingKey: String?,
        val code: String,
        val message: String,
    ): IssueBillingKeyResponse
    @Parcelize
    data class Success(
        val transactionType: TransactionType,
        val billingKey: String,
    ): IssueBillingKeyResponse

    companion object {
        const val TRANSACTION_TYPE = "transactionType"
        const val BILLING_KEY = "billingKey"
        const val CODE = "code"
        const val MESSAGE = "message"
    }
}