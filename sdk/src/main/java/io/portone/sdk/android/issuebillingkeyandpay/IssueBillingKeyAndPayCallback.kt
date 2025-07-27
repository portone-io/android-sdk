package io.portone.sdk.android.issuebillingkeyandpay

import io.portone.sdk.android.Callback
import io.portone.sdk.type.response.IssueBillingKeyAndPayResponse

interface IssueBillingKeyAndPayCallback :
    Callback<IssueBillingKeyAndPayResponse, IssueBillingKeyAndPayResponse> {
    override fun onSuccess(response: IssueBillingKeyAndPayResponse)
    override fun onFail(response: IssueBillingKeyAndPayResponse)
}