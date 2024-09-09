package io.portone.sdk.android

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import io.portone.sdk.billingkey.IssueBillingKeyActivity
import io.portone.sdk.billingkey.IssueBillingKeyCallback
import io.portone.sdk.billingkey.IssueBillingKeyRequest
import io.portone.sdk.identityverification.IdentityVerificationActivity
import io.portone.sdk.identityverification.IdentityVerificationCallback
import io.portone.sdk.identityverification.IdentityVerificationRequest
import io.portone.sdk.payment.PaymentActivity
import io.portone.sdk.payment.PaymentCallback
import io.portone.sdk.payment.PaymentRequest

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

    fun registerForIssueBillingKeyActivity(
        activity: ComponentActivity,
        callback: IssueBillingKeyCallback
    ): ActivityResultLauncher<Intent>

    fun registerForIssueBillingKeyActivity(
        fragment: Fragment,
        callback: IssueBillingKeyCallback
    ): ActivityResultLauncher<Intent>


    fun requestIssueBillingKey(
        activity: ComponentActivity,
        request: IssueBillingKeyRequest,
        resultLauncher: ActivityResultLauncher<Intent>,
    )
    fun registerForIdentityVerificationActivity(
        activity: ComponentActivity,
        callback: IdentityVerificationCallback
    ): ActivityResultLauncher<Intent>

    fun registerForIdentityVerificationActivity(
        fragment: Fragment,
        callback: IdentityVerificationCallback
    ): ActivityResultLauncher<Intent>


    fun requestIdentityVerification(
        activity: ComponentActivity,
        request: IdentityVerificationRequest,
        resultLauncher: ActivityResultLauncher<Intent>,
    )
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
        return registerForActivity(
            activity,
            callback
        )
    }

    override fun registerForPaymentActivity(
        fragment: Fragment,
        callback: PaymentCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            fragment,
            callback
        )
    }

    override fun requestIssueBillingKey(
        activity: ComponentActivity,
        request: IssueBillingKeyRequest,
        resultLauncher: ActivityResultLauncher<Intent>
    ) {
        val bundle = Bundle()
        bundle.putParcelable(REQUEST, request)
        resultLauncher.launch(
            Intent(
                activity,
                IssueBillingKeyActivity::class.java
            ).putExtras(bundle)
        )
    }

    override fun registerForIssueBillingKeyActivity(
        activity: ComponentActivity,
        callback: IssueBillingKeyCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            activity,
            callback
        )
    }

    override fun registerForIssueBillingKeyActivity(
        fragment: Fragment,
        callback: IssueBillingKeyCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            fragment,
            callback
        )
    }

    override fun registerForIdentityVerificationActivity(
        activity: ComponentActivity,
        callback: IdentityVerificationCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            activity,
            callback
        )    }

    override fun registerForIdentityVerificationActivity(
        fragment: Fragment,
        callback: IdentityVerificationCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            fragment,
            callback
        )
    }

    override fun requestIdentityVerification(
        activity: ComponentActivity,
        request: IdentityVerificationRequest,
        resultLauncher: ActivityResultLauncher<Intent>
    ) {
        val bundle = Bundle()
        bundle.putParcelable(REQUEST, request)
        resultLauncher.launch(
            Intent(
                activity,
                IdentityVerificationActivity::class.java
            ).putExtras(bundle)
        )    }

    private inline fun <reified S : Parcelable, reified F : Parcelable, C : Callback<S, F>> registerForActivity(
        activity: ComponentActivity,
        callback: C
    ): ActivityResultLauncher<Intent> {
        return activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                SUCCESS_CODE -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.data?.getParcelableExtra(
                            RESPONSE,
                            S::class.java
                        )
                            ?.let { callback.onSuccess(it) }
                    } else {
                        result.data?.getParcelableExtra<S>(RESPONSE)
                            ?.let { callback.onSuccess(it) }
                    }
                }

                FAIL_CODE -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.data?.getParcelableExtra(
                            RESPONSE,
                            F::class.java
                        )
                            ?.let { callback.onFail(it) }
                    } else {
                        result.data?.getParcelableExtra<F>(RESPONSE)
                            ?.let { callback.onFail(it) }
                    }
                }
            }

        }
    }

    private inline fun <reified S : Parcelable, reified F : Parcelable, C : Callback<S, F>> registerForActivity(
        fragment: Fragment,
        callback: C
    ): ActivityResultLauncher<Intent> {
        return fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                SUCCESS_CODE -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.data?.getParcelableExtra(
                            RESPONSE,
                            S::class.java
                        )
                            ?.let { callback.onSuccess(it) }
                    } else {
                        result.data?.getParcelableExtra<S>(RESPONSE)
                            ?.let { callback.onSuccess(it) }
                    }
                }

                FAIL_CODE -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        result.data?.getParcelableExtra(
                            RESPONSE,
                            F::class.java
                        )
                            ?.let { callback.onFail(it) }
                    } else {
                        result.data?.getParcelableExtra<F>(RESPONSE)
                            ?.let { callback.onFail(it) }
                    }
                }
            }

        }
    }


}