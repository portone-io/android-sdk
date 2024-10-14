package io.portone.sdk.android.issuebillingkey

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.PortOneWebView
import io.portone.sdk.android.R

class IssueBillingKeyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_billing_key)
        val issueBillingKeyRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PortOne.REQUEST, IssueBillingKeyRequest::class.java)
        } else {
            intent.getParcelableExtra(PortOne.REQUEST)

        }
        val webView = findViewById<PortOneWebView>(R.id.web_view_issue_billing_key)
        if (issueBillingKeyRequest != null) {
            webView.requestIssueBillingKey(
                issueBillingKeyRequest,
                object : IssueBillingKeyCallback {
                    override fun onSuccess(response: IssueBillingKeyResponse.Success) {
                        setResult(
                            PortOne.SUCCESS_CODE,
                            Intent().putExtra(PortOne.RESPONSE, response)
                        )
                        finish()
                    }

                    override fun onFail(response: IssueBillingKeyResponse.Fail) {
                        setResult(PortOne.FAIL_CODE, Intent().putExtra(PortOne.RESPONSE, response))
                        finish()
                    }
                })
        }
    }
}