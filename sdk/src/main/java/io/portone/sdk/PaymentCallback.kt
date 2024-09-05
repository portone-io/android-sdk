package io.portone.sdk

interface PaymentCallback: Callback<PaymentResponse.Success, PaymentResponse.Fail> {
    override fun onSuccess(response: PaymentResponse.Success)
    override fun onFail(response: PaymentResponse.Fail)
}