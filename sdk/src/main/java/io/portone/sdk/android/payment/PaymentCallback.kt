package io.portone.sdk.android.payment

import io.portone.sdk.android.Callback
import io.portone.sdk.type.response.PaymentResponse

interface PaymentCallback: Callback<PaymentResponse, PaymentResponse> {
    override fun onSuccess(response: PaymentResponse)
    override fun onFail(response: PaymentResponse)
}