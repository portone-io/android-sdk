package io.portone.sdk.android

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import io.portone.sdk.android.identityverification.IdentityVerificationActivity
import io.portone.sdk.android.identityverification.IdentityVerificationCallback
import io.portone.sdk.android.identityverification.IdentityVerificationRequest
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyActivity
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyCallback
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyRequest
import io.portone.sdk.android.payment.PaymentActivity
import io.portone.sdk.android.payment.PaymentCallback
import io.portone.sdk.android.payment.PaymentRequest
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayActivity
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayCallback
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayRequest
import io.portone.sdk.android.issuebillingkeyui.LoadIssueBillingKeyUIActivity
import io.portone.sdk.android.paymentui.LoadPaymentUIActivity
import io.portone.sdk.android.paymentui.LoadPaymentUIRequest
import io.portone.sdk.android.issuebillingkeyui.LoadIssueBillingKeyUIRequest

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

    fun registerForIssueBillingKeyAndPay(
        activity: ComponentActivity,
        callback: IssueBillingKeyAndPayCallback
    ): ActivityResultLauncher<Intent>

    fun registerForIssueBillingKeyAndPay(
        fragment: Fragment,
        callback: IssueBillingKeyAndPayCallback
    ): ActivityResultLauncher<Intent>


    fun requestIssueBillingKeyAndPay(
        activity: ComponentActivity,
        request: IssueBillingKeyAndPayRequest,
        resultLauncher: ActivityResultLauncher<Intent>,
    )

    fun registerForLoadPaymentUI(
        activity: ComponentActivity,
        callback: PaymentCallback
    ): ActivityResultLauncher<Intent>

    fun registerForLoadPaymentUI(
        fragment: Fragment,
        callback: PaymentCallback
    ): ActivityResultLauncher<Intent>

    fun loadPaymentUI(
        activity: ComponentActivity,
        request: LoadPaymentUIRequest,
        resultLauncher: ActivityResultLauncher<Intent>,
    )

    fun registerForLoadIssueBillingKeyUI(
        activity: ComponentActivity,
        callback: IssueBillingKeyCallback
    ): ActivityResultLauncher<Intent>

    fun registerForLoadIssueBillingKeyUI(
        fragment: Fragment,
        callback: IssueBillingKeyCallback
    ): ActivityResultLauncher<Intent>

    fun loadIssueBillingKeyUI(
        activity: ComponentActivity,
        request: LoadIssueBillingKeyUIRequest,
        resultLauncher: ActivityResultLauncher<Intent>,
    )
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
        )
    }

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
        )
    }

    override fun registerForIssueBillingKeyAndPay(
        activity: ComponentActivity,
        callback: IssueBillingKeyAndPayCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            activity,
            callback
        )
    }

    override fun registerForIssueBillingKeyAndPay(
        fragment: Fragment,
        callback: IssueBillingKeyAndPayCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            fragment,
            callback
        )
    }

    override fun requestIssueBillingKeyAndPay(
        activity: ComponentActivity,
        request: IssueBillingKeyAndPayRequest,
        resultLauncher: ActivityResultLauncher<Intent>
    ) {
        val bundle = Bundle()
        bundle.putParcelable(REQUEST, request)
        resultLauncher.launch(
            Intent(
                activity,
                IssueBillingKeyAndPayActivity::class.java
            ).putExtras(bundle)
        )
    }

    override fun registerForLoadPaymentUI(
        activity: ComponentActivity,
        callback: PaymentCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            activity,
            callback
        )
    }

    override fun registerForLoadPaymentUI(
        fragment: Fragment,
        callback: PaymentCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            fragment,
            callback
        )
    }

    override fun loadPaymentUI(
        activity: ComponentActivity,
        request: LoadPaymentUIRequest,
        resultLauncher: ActivityResultLauncher<Intent>
    ) {
        val bundle = Bundle()
        bundle.putParcelable(REQUEST, request)
        resultLauncher.launch(
            Intent(
                activity,
                LoadPaymentUIActivity::class.java
            ).putExtras(bundle)
        )
    }

    override fun registerForLoadIssueBillingKeyUI(
        activity: ComponentActivity,
        callback: IssueBillingKeyCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            activity,
            callback
        )
    }

    override fun registerForLoadIssueBillingKeyUI(
        fragment: Fragment,
        callback: IssueBillingKeyCallback
    ): ActivityResultLauncher<Intent> {
        return registerForActivity(
            fragment,
            callback
        )
    }

    override fun loadIssueBillingKeyUI(
        activity: ComponentActivity,
        request: LoadIssueBillingKeyUIRequest,
        resultLauncher: ActivityResultLauncher<Intent>
    ) {
        val bundle = Bundle()
        bundle.putParcelable(REQUEST, request)
        resultLauncher.launch(
            Intent(
                activity,
                LoadIssueBillingKeyUIActivity::class.java
            ).putExtras(bundle)
        )
    }

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