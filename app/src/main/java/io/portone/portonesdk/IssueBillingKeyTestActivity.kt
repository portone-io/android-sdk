package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.portone.portonesdk.databinding.ActivityIssueBillingKeyTestBinding
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyCallback
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyRequest
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyResponse
import io.portone.sdk.android.PortOne
import kotlinx.serialization.json.Json

class IssueBillingKeyTestActivity : BaseActivity<ActivityIssueBillingKeyTestBinding>() {
    private val issueBillingKeyActivityResultLauncher =
        PortOne.registerForIssueBillingKeyActivity(this, callback = object :
            IssueBillingKeyCallback {
            override fun onSuccess(response: IssueBillingKeyResponse.Success) {
                AlertDialog.Builder(this@IssueBillingKeyTestActivity)
                    .setTitle("빌링키 발급 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: IssueBillingKeyResponse.Fail) {
                AlertDialog.Builder(this@IssueBillingKeyTestActivity)
                    .setTitle("빌링키 발급 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnIssueBillingKey.setOnClickListener {
            try {
                PortOne.requestIssueBillingKey(
                    this,
                    request = Json.decodeFromString<IssueBillingKeyRequest>(binding.etIssueBillingKeyJson.text.toString()),
                    resultLauncher = issueBillingKeyActivityResultLauncher
                )
            } catch (e: Exception) {
                println(e.toString())
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun setViewBinding(inflater: LayoutInflater): ActivityIssueBillingKeyTestBinding {
        return ActivityIssueBillingKeyTestBinding.inflate(inflater)
    }
}