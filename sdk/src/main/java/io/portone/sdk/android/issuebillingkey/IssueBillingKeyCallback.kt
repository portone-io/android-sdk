package io.portone.sdk.android.issuebillingkey

import io.portone.sdk.android.Callback
import io.portone.sdk.__generated__.response.IssueBillingKeyResponse

interface IssueBillingKeyCallback :
    Callback<IssueBillingKeyResponse, IssueBillingKeyResponse> {
    override fun onSuccess(response: IssueBillingKeyResponse)
    override fun onFail(response: IssueBillingKeyResponse)
}