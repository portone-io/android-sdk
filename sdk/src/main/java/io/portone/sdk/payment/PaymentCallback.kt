package io.portone.sdk.payment

import io.portone.sdk.Callback

interface PaymentCallback: Callback<PaymentResponse.Success, PaymentResponse.Fail> {
    override fun onSuccess(response: PaymentResponse.Success)
    override fun onFail(response: PaymentResponse.Fail)
}