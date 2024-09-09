package io.portone.sdk.identityverification

import android.os.Parcelable
import io.portone.sdk.TransactionType
import kotlinx.parcelize.Parcelize


@Parcelize
sealed interface IdentityVerificationResponse : Parcelable {
    @Parcelize
    data class Fail(
        val transactionType: TransactionType?,
        val identityVerificationTxId: String?,
        val code: String,
        val message: String,
    ) : IdentityVerificationResponse

    @Parcelize
    data class Success(
        val transactionType: TransactionType,
        val identityVerificationId: String,
        val identityVerificationTxId: String,
    ) : IdentityVerificationResponse

    companion object {
        const val TRANSACTION_TYPE = "transactionType"
        const val IDENTITY_VERIFICATION_ID = "identityVerificationId"
        const val IDENTITY_VERIFICATION_TX_ID = "identityVerificationTxId"
        const val CODE = "code"
        const val MESSAGE = "message"
    }
}