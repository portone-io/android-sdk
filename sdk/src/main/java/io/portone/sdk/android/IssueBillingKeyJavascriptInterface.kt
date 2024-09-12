package io.portone.sdk.android

interface IssueBillingKeyJavascriptInterface {
    fun fail(
        transactionType: String?,
        billingKey: String?,
        code: String,
        message: String
    )
}