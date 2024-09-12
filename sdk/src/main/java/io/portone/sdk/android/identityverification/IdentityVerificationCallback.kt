package io.portone.sdk.android.identityverification

import io.portone.sdk.android.Callback

interface IdentityVerificationCallback :
    Callback<IdentityVerificationResponse.Success, IdentityVerificationResponse.Fail> {
    override fun onSuccess(response: IdentityVerificationResponse.Success)
    override fun onFail(response: IdentityVerificationResponse.Fail)
}