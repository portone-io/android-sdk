package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.portone.portonesdk.databinding.ActivityIssueBillingKeyAndPayTestBinding
import io.portone.sdk.PortOne
import io.portone.sdk.issuebillingkeyandpay.IssueBillingKeyAndPayCallback
import io.portone.sdk.issuebillingkeyandpay.IssueBillingKeyAndPayRequest
import io.portone.sdk.issuebillingkeyandpay.IssueBillingKeyAndPayResponse
import kotlinx.serialization.json.Json

class IssueBillingKeyAndPayTestActivity : BaseActivity<ActivityIssueBillingKeyAndPayTestBinding>() {
    private val issueBillingKeyAndPayActivityResultLauncher =
        PortOne.registerForIssueBillingKeyAndPay(this, callback = object :
            IssueBillingKeyAndPayCallback {
            override fun onSuccess(response: IssueBillingKeyAndPayResponse.Success) {
                AlertDialog.Builder(this@IssueBillingKeyAndPayTestActivity)
                    .setTitle("빌링키 발급 및 결제 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: IssueBillingKeyAndPayResponse.Fail) {
                AlertDialog.Builder(this@IssueBillingKeyAndPayTestActivity)
                    .setTitle("빌링키 발급 및 결제 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnIssueBillingKeyAndPay.setOnClickListener {
            try {
                PortOne.requestIssueBillingKeyAndPay(
                    this,
                    request = Json.decodeFromString<IssueBillingKeyAndPayRequest>(binding.etIssueBillingKeyAndPayJson.text.toString()),
                    resultLauncher = issueBillingKeyAndPayActivityResultLauncher
                )
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setViewBinding(inflater: LayoutInflater): ActivityIssueBillingKeyAndPayTestBinding {
        return ActivityIssueBillingKeyAndPayTestBinding.inflate(inflater)
    }
}