package io.portone.sdk.identityverification

import io.portone.sdk.Callback

interface IdentityVerificationCallback :
    Callback<IdentityVerificationResponse.Success, IdentityVerificationResponse.Fail> {
    override fun onSuccess(response: IdentityVerificationResponse.Success)
    override fun onFail(response: IdentityVerificationResponse.Fail)
}