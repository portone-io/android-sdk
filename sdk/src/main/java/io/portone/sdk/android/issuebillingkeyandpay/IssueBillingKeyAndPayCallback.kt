package io.portone.sdk.android.issuebillingkeyandpay

import io.portone.sdk.android.Callback

interface IssueBillingKeyAndPayCallback :
    Callback<IssueBillingKeyAndPayResponse.Success, IssueBillingKeyAndPayResponse.Fail> {
    override fun onSuccess(response: IssueBillingKeyAndPayResponse.Success)
    override fun onFail(response: IssueBillingKeyAndPayResponse.Fail)
}