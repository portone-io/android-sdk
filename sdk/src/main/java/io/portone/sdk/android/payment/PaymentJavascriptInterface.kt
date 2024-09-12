package io.portone.sdk.android.payment

interface PaymentJavascriptInterface {
    fun fail(
        transactionType: String?,
        txId: String?,
        paymentId: String?,
        code: String,
        message: String,
    )
}