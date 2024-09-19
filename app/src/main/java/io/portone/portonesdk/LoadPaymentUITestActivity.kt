package io.portone.portonesdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import io.portone.portonesdk.databinding.ActivityLoadPaymentUiTestBinding
import io.portone.sdk.android.PaymentUIType
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.payment.PaymentCallback
import io.portone.sdk.android.payment.PaymentResponse
import io.portone.sdk.android.paymentui.LoadPaymentUIRequest
import io.portone.sdk.android.type.Amount
import io.portone.sdk.android.type.Currency
import io.portone.sdk.android.type.Customer

class LoadPaymentUITestActivity : BaseActivity<ActivityLoadPaymentUiTestBinding>() {
    private val loadPaymentUIActivityResultLauncher =
        PortOne.registerForLoadPaymentUI(this, callback = object :
            PaymentCallback {
            override fun onSuccess(response: PaymentResponse.Success) {
                AlertDialog.Builder(this@LoadPaymentUITestActivity)
                    .setTitle("결제 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: PaymentResponse.Fail) {
                AlertDialog.Builder(this@LoadPaymentUITestActivity)
                    .setTitle("결제 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnLoadPaymentUi.setOnClickListener {
            PortOne.loadPaymentUI(
                this,
                request = LoadPaymentUIRequest(
                    uiType = PaymentUIType.PAYPAL_SPB,
                    storeId = binding.etStoreId.text.toString(),
                    paymentId = binding.etPaymentId.text.toString(),
                    orderName = binding.etOrderName.text.toString(),
                    channelKey = binding.etChannelKey.text.toString(),
                    amount = Amount(
                        total = binding.etTotalAmount.text.toString().toLong(),
                        currency = Currency.valueOf(binding.etCurrency.text.toString()),
                    ),
                    customer = Customer(
                        name = Customer.Name.Full("test")
                    ),
                ),
                resultLauncher = loadPaymentUIActivityResultLauncher,
            )
        }
    }

    override fun setViewBinding(inflater: LayoutInflater): ActivityLoadPaymentUiTestBinding {
        return ActivityLoadPaymentUiTestBinding.inflate(inflater)
    }
}