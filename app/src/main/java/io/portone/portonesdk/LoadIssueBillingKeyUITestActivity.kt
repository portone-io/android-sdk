package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.portone.portonesdk.databinding.ActivityLoadIssueBillingKeyUiTestBinding
import io.portone.sdk.android.IssueBillingKeyUIType
import io.portone.sdk.android.PaymentUIType
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyCallback
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyResponse
import io.portone.sdk.android.issuebillingkeyui.LoadIssueBillingKeyUIRequest
import io.portone.sdk.android.payment.PaymentCallback
import io.portone.sdk.android.payment.PaymentResponse
import io.portone.sdk.android.paymentui.LoadPaymentUIRequest
import io.portone.sdk.android.type.Amount
import io.portone.sdk.android.type.BillingKeyMethod
import io.portone.sdk.android.type.Currency
import io.portone.sdk.android.type.Customer

class LoadIssueBillingKeyUITestActivity : BaseActivity<ActivityLoadIssueBillingKeyUiTestBinding>() {
    private val loadIssueBillingKeyUIActivityResultLauncher =
        PortOne.registerForLoadIssueBillingKeyUI(this, callback = object :
            IssueBillingKeyCallback {
            override fun onSuccess(response: IssueBillingKeyResponse.Success) {
                AlertDialog.Builder(this@LoadIssueBillingKeyUITestActivity)
                    .setTitle("결제 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: IssueBillingKeyResponse.Fail) {
                AlertDialog.Builder(this@LoadIssueBillingKeyUITestActivity)
                    .setTitle("결제 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnLoadIssueBillingKeyUi.setOnClickListener {
            PortOne.loadIssueBillingKeyUI(
                this,
                request = LoadIssueBillingKeyUIRequest(
                    uiType = IssueBillingKeyUIType.PAYPAL_RT,
                    billingKeyMethod = BillingKeyMethod.Paypal,
                    storeId = binding.etStoreId.text.toString(),
                    channelKey = binding.etChannelKey.text.toString(),
                ),
                resultLauncher = loadIssueBillingKeyUIActivityResultLauncher,
            )
        }
    }

    override fun setViewBinding(inflater: LayoutInflater): ActivityLoadIssueBillingKeyUiTestBinding {
        return ActivityLoadIssueBillingKeyUiTestBinding.inflate(inflater)
    }
}