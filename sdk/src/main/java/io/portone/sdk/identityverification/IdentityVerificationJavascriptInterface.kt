package io.portone.sdk.identityverification

interface IdentityVerificationJavascriptInterface {
    fun fail(
        transactionType: String?,
        identityVerificationTxId: String?,
        code: String,
        message: String
    )
}