package io.portone.sdk.android

interface PaymentJavascriptInterface {
    fun fail(
        transactionType: String?,
        txId: String?,
        paymentId: String?,
        code: String,
        message: String,
    )
}