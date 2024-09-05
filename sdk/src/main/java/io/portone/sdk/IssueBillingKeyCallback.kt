package io.portone.sdk

interface PaymentCallback {
    fun onSuccess(response: PaymentResponse.Success)
    fun onFail(response: PaymentResponse.Fail)
}