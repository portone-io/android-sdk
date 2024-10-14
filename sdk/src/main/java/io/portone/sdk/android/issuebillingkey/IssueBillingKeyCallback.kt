package io.portone.sdk.android.issuebillingkey

import io.portone.sdk.android.Callback

interface IssueBillingKeyCallback :
    Callback<IssueBillingKeyResponse.Success, IssueBillingKeyResponse.Fail> {
    override fun onSuccess(response: IssueBillingKeyResponse.Success)
    override fun onFail(response: IssueBillingKeyResponse.Fail)
}