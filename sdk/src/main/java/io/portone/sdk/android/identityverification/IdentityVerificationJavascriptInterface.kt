package io.portone.sdk.android.identityverification

interface IdentityVerificationJavascriptInterface {
    fun fail(
        transactionType: String?,
        identityVerificationTxId: String?,
        code: String,
        message: String
    )
}