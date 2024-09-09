package io.portone.sdk.billingkey

import io.portone.sdk.Callback

interface IssueBillingKeyCallback :
    Callback<IssueBillingKeyResponse.Success, IssueBillingKeyResponse.Fail> {
    override fun onSuccess(response: IssueBillingKeyResponse.Success)
    override fun onFail(response: IssueBillingKeyResponse.Fail)
}