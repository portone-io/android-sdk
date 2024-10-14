package io.portone.sdk.android.issuebillingkeyandpay

interface IssueBillingKeyAndPayJavascriptInterface {
    fun fail(
        transactionType: String?,
        txId: String?,
        paymentId: String?,
        billingKey: String?,
        code: String,
        message: String
    )
}