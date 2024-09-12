package io.portone.sdk.android.payment

import io.portone.sdk.android.Callback

interface PaymentCallback: Callback<PaymentResponse.Success, PaymentResponse.Fail> {
    override fun onSuccess(response: PaymentResponse.Success)
    override fun onFail(response: PaymentResponse.Fail)
}