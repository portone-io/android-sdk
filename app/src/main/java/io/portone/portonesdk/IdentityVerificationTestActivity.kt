package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.portone.portonesdk.databinding.ActivityIdentityVerificationTestBinding
import io.portone.sdk.PortOne
import io.portone.sdk.identityverification.IdentityVerificationCallback
import io.portone.sdk.identityverification.IdentityVerificationRequest
import io.portone.sdk.identityverification.IdentityVerificationResponse
import kotlinx.serialization.json.Json

class IdentityVerificationTestActivity : BaseActivity<ActivityIdentityVerificationTestBinding>() {
    private val identityVerificationActivityResultLauncher =
        PortOne.registerForIdentityVerificationActivity(this, callback = object :
            IdentityVerificationCallback {
            override fun onSuccess(response: IdentityVerificationResponse.Success) {
                AlertDialog.Builder(this@IdentityVerificationTestActivity)
                    .setTitle("본인인증 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: IdentityVerificationResponse.Fail) {
                AlertDialog.Builder(this@IdentityVerificationTestActivity)
                    .setTitle("본인인증 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnIdentityVerification.setOnClickListener {
            try {
                PortOne.requestIdentityVerification(
                    this,
                    request = Json.decodeFromString<IdentityVerificationRequest>(binding.etIdentityVerificationJson.text.toString()),
                    resultLauncher = identityVerificationActivityResultLauncher
                )
            } catch (e: Exception) {
                println(e.toString())
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setViewBinding(inflater: LayoutInflater): ActivityIdentityVerificationTestBinding {
        return ActivityIdentityVerificationTestBinding.inflate(inflater)
    }
}