package io.portone.sdk.android.issuebillingkeyandpay

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.PortOneWebView
import io.portone.sdk.android.R

class IssueBillingKeyAndPayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_billing_key_and_pay)
        val issueBillingKeyAndPayRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PortOne.REQUEST, IssueBillingKeyAndPayRequest::class.java)
        } else {
            intent.getParcelableExtra(PortOne.REQUEST)

        }
        val webView = findViewById<PortOneWebView>(R.id.web_view_issue_billing_key_and_pay)
        if (issueBillingKeyAndPayRequest != null) {
            webView.requestIssueBillingKeyAndPay(
                issueBillingKeyAndPayRequest,
                object : IssueBillingKeyAndPayCallback {
                    override fun onSuccess(response: IssueBillingKeyAndPayResponse.Success) {
                        setResult(
                            PortOne.SUCCESS_CODE,
                            Intent().putExtra(PortOne.RESPONSE, response)
                        )
                        finish()
                    }

                    override fun onFail(response: IssueBillingKeyAndPayResponse.Fail) {
                        setResult(PortOne.FAIL_CODE, Intent().putExtra(PortOne.RESPONSE, response))
                        finish()
                    }
                })
        }
    }
}
