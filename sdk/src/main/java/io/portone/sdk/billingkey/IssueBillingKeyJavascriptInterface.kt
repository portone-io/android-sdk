package io.portone.sdk.billingkey

interface IssueBillingKeyJavascriptInterface {
    fun fail(
        transactionType: String?,
        billingKey: String?,
        code: String,
        message: String
    )
}