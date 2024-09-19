package io.portone.sdk.android.issuebillingkeyui

interface LoadIssueBillingKeyUIJavascriptInterface {
    fun fail(
        transactionType: String?,
        billingKey: String?,
        code: String,
        message: String
    )
    fun success(
        transactionType: String,
        billingKey: String,
    )
}