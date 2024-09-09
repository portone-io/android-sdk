package io.portone.sdk.payment

import android.os.Parcelable
import io.portone.sdk.TransactionType
import kotlinx.parcelize.Parcelize


@Parcelize
sealed interface PaymentResponse : Parcelable {
    @Parcelize
    data class Fail(
        val transactionType: TransactionType?,
        val txId: String?,
        val paymentId: String?,
        val code: String,
        val message: String,
    ): PaymentResponse
    @Parcelize
    data class Success(
        val transactionType: TransactionType,
        val txId: String,
        val paymentId: String
    ): PaymentResponse

    companion object {
        const val TRANSACTION_TYPE = "transactionType"
        const val TX_ID = "txId"
        const val PAYMENT_ID = "paymentId"
        const val CODE = "code"
        const val MESSAGE = "message"
    }
}