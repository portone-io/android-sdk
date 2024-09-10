package io.portone.sdk.android

interface PaymentCallback {
    fun onSuccess(response: PaymentResponse.Success)
    fun onFail(response: PaymentResponse.Fail)
}