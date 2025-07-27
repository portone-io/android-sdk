package io.portone.sdk.android.issuebillingkeyandpay

import io.portone.sdk.android.Callback
import io.portone.sdk.__generated__.response.IssueBillingKeyAndPayResponse

interface IssueBillingKeyAndPayCallback :
    Callback<IssueBillingKeyAndPayResponse, IssueBillingKeyAndPayResponse> {
    override fun onSuccess(response: IssueBillingKeyAndPayResponse)
    override fun onFail(response: IssueBillingKeyAndPayResponse)
}