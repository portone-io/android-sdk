package io.portone.sdk.android.issuebillingkey

interface IssueBillingKeyJavascriptInterface {
    fun fail(
        transactionType: String?,
        billingKey: String?,
        code: String,
        message: String
    )
}