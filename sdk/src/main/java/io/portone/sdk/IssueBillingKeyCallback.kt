package io.portone.sdk

interface IssueBillingKeyCallback : Callback<IssueBillingKeyResponse.Success, IssueBillingKeyResponse.Fail> {
    override fun onSuccess(response: IssueBillingKeyResponse.Success)
    override fun onFail(response: IssueBillingKeyResponse.Fail)
}