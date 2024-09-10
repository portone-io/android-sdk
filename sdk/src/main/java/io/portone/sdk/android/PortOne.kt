package io.portone.sdk.android

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

interface Sdk {
    fun registerForPaymentActivity(
        activity: ComponentActivity,
        callback: PaymentCallback
    ): ActivityResultLauncher<Intent>
    fun registerForPaymentActivity(
        fragment: Fragment,
        callback: PaymentCallback
    ): ActivityResultLauncher<Intent>
    fun requestPayment(
        activity: ComponentActivity,
        request: PaymentRequest,
        resultLauncher: ActivityResultLauncher<Intent>,
    )
//    TODO
//    fun requestIssueBillingKey(request: IssueBillingKeyRequest)
//
//    fun requestIssueBillingKeyAndPay(request: IssueBillingKeyAndPayRequest)
//
//    fun requestIdentityVerification(request: IdentityVerificationRequest)
//
//    fun loadPaymentUI(request: LoadPaymentUIRequest)
//
//    fun loadIssueBillingKeyUI(request: LoadIssueBillingKeyUIRequest)
}

object PortOne : Sdk {
    internal const val SUCCESS_CODE = 200
    internal const val FAIL_CODE = 500
    internal const val REQUEST = "request"
    internal const val RESPONSE = "response"
    internal const val REDIRECT_URL = "portone://payment"

    override fun requestPayment(
        activity: ComponentActivity,
        request: PaymentRequest,
        resultLauncher: ActivityResultLauncher<Intent>,
    ) {
        val bundle = Bundle()
        bundle.putParcelable(REQUEST, request)
        resultLauncher.launch(Intent(activity, PaymentActivity::class.java).putExtras(bundle))
    }

    override fun registerForPaymentActivity(
        activity: ComponentActivity,
        callback: PaymentCallback
    ): ActivityResultLauncher<Intent> {
        return activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                SUCCESS_CODE -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.data?.getParcelableExtra(
                            RESPONSE,
                            PaymentResponse.Success::class.java
                        )
                            ?.let { callback.onSuccess(it) }
                    } else {
                        result.data?.getParcelableExtra<PaymentResponse.Success>(RESPONSE)
                            ?.let { callback.onSuccess(it) }
                    }
                }

                FAIL_CODE -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.data?.getParcelableExtra(
                            RESPONSE,
                            PaymentResponse.Fail::class.java
                        )
                            ?.let { callback.onFail(it) }
                    } else {
                        result.data?.getParcelableExtra<PaymentResponse.Fail>(RESPONSE)
                            ?.let { callback.onFail(it) }
                    }
                }
            }

        }
    }
    override fun registerForPaymentActivity(
        fragment: Fragment,
        callback: PaymentCallback
    ): ActivityResultLauncher<Intent> {
        return fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                SUCCESS_CODE -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.data?.getParcelableExtra(
                            RESPONSE,
                            PaymentResponse.Success::class.java
                        )
                            ?.let { callback.onSuccess(it) }
                    } else {
                        result.data?.getParcelableExtra<PaymentResponse.Success>(RESPONSE)
                            ?.let { callback.onSuccess(it) }
                    }
                }

                FAIL_CODE -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.data?.getParcelableExtra(
                            RESPONSE,
                            PaymentResponse.Fail::class.java
                        )
                            ?.let { callback.onFail(it) }
                    } else {
                        result.data?.getParcelableExtra<PaymentResponse.Fail>(RESPONSE)
                            ?.let { callback.onFail(it) }
                    }
                }
            }

        }
    }

}