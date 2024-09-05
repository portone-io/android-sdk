package io.portone.sdk

interface IssueBillingKeyJavascriptInterface {
    fun fail(
        transactionType: String?,
        billingKey: String?,
        code: String,
        message: String
    )
}