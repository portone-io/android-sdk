package io.portone.sdk.android.paymentui

interface LoadPaymentUIJavascriptInterface {
    fun fail(
        transactionType: String?,
        txId: String?,
        paymentId: String?,
        code: String,
        message: String,
    )
    fun success(
        transactionType: String,
        txId: String,
        paymentId: String,
    )
}