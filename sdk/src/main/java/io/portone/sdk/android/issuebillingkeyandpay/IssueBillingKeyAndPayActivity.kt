package io.portone.sdk.android.issuebillingkeyandpay

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.PortOneWebView
import io.portone.sdk.android.R
import io.portone.sdk.android.util.applyInsets
import io.portone.sdk.android.type.request.IssueBillingKeyAndPayRequest
import io.portone.sdk.android.type.response.IssueBillingKeyAndPayResponse

class IssueBillingKeyAndPayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_billing_key_and_pay)
        applyInsets(findViewById(R.id.main))
        val issueBillingKeyAndPayRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PortOne.REQUEST, IssueBillingKeyAndPayRequest::class.java)
        } else {
            intent.getParcelableExtra(PortOne.REQUEST)

        }
        val webView = findViewById<PortOneWebView>(R.id.web_view_issue_billing_key_and_pay)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        if (issueBillingKeyAndPayRequest != null) {
            webView.requestIssueBillingKeyAndPay(
                issueBillingKeyAndPayRequest,
                object : IssueBillingKeyAndPayCallback {
                    override fun onSuccess(response: IssueBillingKeyAndPayResponse) {
                        setResult(
                            PortOne.SUCCESS_CODE,
                            Intent().putExtra(PortOne.RESPONSE, response)
                        )
                        finish()
                    }

                    override fun onFail(response: IssueBillingKeyAndPayResponse) {
                        setResult(PortOne.FAIL_CODE, Intent().putExtra(PortOne.RESPONSE, response))
                        finish()
                    }
                })
        }
    }
}
