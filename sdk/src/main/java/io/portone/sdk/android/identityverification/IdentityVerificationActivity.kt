package io.portone.sdk.android.identityverification

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.PortOneWebView
import io.portone.sdk.android.R

class IdentityVerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identity_verification)
        val identityVerificationRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PortOne.REQUEST, IdentityVerificationRequest::class.java)
        } else {
            intent.getParcelableExtra(PortOne.REQUEST)

        }
        val webView = findViewById<PortOneWebView>(R.id.web_view_identity_verification)
        if (identityVerificationRequest != null) {
            webView.requestIdentityVerification(
                identityVerificationRequest,
                object : IdentityVerificationCallback {
                    override fun onSuccess(response: IdentityVerificationResponse.Success) {
                        setResult(
                            PortOne.SUCCESS_CODE,
                            Intent().putExtra(PortOne.RESPONSE, response)
                        )
                        finish()
                    }

                    override fun onFail(response: IdentityVerificationResponse.Fail) {
                        setResult(PortOne.FAIL_CODE, Intent().putExtra(PortOne.RESPONSE, response))
                        finish()
                    }
                })
        }
    }
}