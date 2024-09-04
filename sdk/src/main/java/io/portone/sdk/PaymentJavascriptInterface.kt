package io.portone.sdk

interface PaymentJavascriptInterface {
    fun fail(
        transactionType: String?,
        txId: String?,
        paymentId: String?,
        code: String,
        message: String,
    )
}