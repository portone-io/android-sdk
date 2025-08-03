package io.portone.sdk.android.issuebillingkeyui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.PortOneWebView
import io.portone.sdk.android.R
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyCallback
import io.portone.sdk.android.util.applyInsets
import io.portone.sdk.android.type.response.IssueBillingKeyResponse
import io.portone.sdk.android.type.request.LoadIssueBillingKeyUIRequest

class LoadIssueBillingKeyUIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_issue_billing_key_ui_activity)
        applyInsets(findViewById(R.id.main))

        val loadIssueBillingKeyUIRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PortOne.REQUEST, LoadIssueBillingKeyUIRequest::class.java)
        } else {
            intent.getParcelableExtra(PortOne.REQUEST)

        }
        val webView = findViewById<PortOneWebView>(R.id.web_view_load_issue_billing_key_ui)
        if (loadIssueBillingKeyUIRequest != null) {
            webView.loadIssueBillingKeyUI(loadIssueBillingKeyUIRequest, object :
                IssueBillingKeyCallback {
                override fun onSuccess(response: IssueBillingKeyResponse) {
                    setResult(PortOne.SUCCESS_CODE, Intent().putExtra(PortOne.RESPONSE, response))
                    finish()
                }

                override fun onFail(response: IssueBillingKeyResponse) {
                    setResult(PortOne.FAIL_CODE, Intent().putExtra(PortOne.RESPONSE, response))
                    finish()
                }
            })
        }
    }
}