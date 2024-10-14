package io.portone.sdk.android.issuebillingkeyandpay

import android.os.Parcelable
import io.portone.sdk.android.TransactionType
import kotlinx.parcelize.Parcelize


@Parcelize
sealed interface IssueBillingKeyAndPayResponse : Parcelable {
    @Parcelize
    data class Fail(
        val transactionType: TransactionType?,
        val txId: String?,
        val paymentId: String?,
        val billingKey: String?,
        val code: String,
        val message: String,
    ) : IssueBillingKeyAndPayResponse

    @Parcelize
    data class Success(
        val transactionType: TransactionType,
        val txId: String,
        val paymentId: String,
        val billingKey: String,
    ) : IssueBillingKeyAndPayResponse

    companion object {
        const val TRANSACTION_TYPE = "transactionType"
        const val BILLING_KEY = "billingKey"
        const val PAYMENT_ID = "paymentId"
        const val TX_ID = "txId"
        const val CODE = "code"
        const val MESSAGE = "message"
    }
}