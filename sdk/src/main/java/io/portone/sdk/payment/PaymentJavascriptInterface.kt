package io.portone.sdk.payment

interface PaymentJavascriptInterface {
    fun fail(
        transactionType: String?,
        txId: String?,
        paymentId: String?,
        code: String,
        message: String,
    )
}