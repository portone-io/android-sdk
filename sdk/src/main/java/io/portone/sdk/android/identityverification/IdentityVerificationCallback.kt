package io.portone.sdk.android.identityverification

import io.portone.sdk.android.Callback
import io.portone.sdk.type.response.IdentityVerificationResponse

interface IdentityVerificationCallback :
    Callback<IdentityVerificationResponse, IdentityVerificationResponse> {
    override fun onSuccess(response: IdentityVerificationResponse)
    override fun onFail(response: IdentityVerificationResponse)
}