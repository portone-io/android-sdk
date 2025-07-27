package io.portone.sdk.android.identityverification

interface IdentityVerificationJavascriptInterface {
    fun fail(
        transactionType: String,
        identityVerificationTxId: String,
        identityVerificationId: String,
        code: String,
        message: String,
        pgCode: String?,
        pgMessage: String?
    )
}