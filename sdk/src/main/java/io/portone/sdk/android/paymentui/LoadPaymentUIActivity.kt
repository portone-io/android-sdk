package io.portone.sdk.android.paymentui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.PortOneWebView
import io.portone.sdk.android.R
import io.portone.sdk.android.payment.PaymentCallback
import io.portone.sdk.android.util.applyInsets
import io.portone.sdk.android.type.response.PaymentResponse
import io.portone.sdk.android.type.request.LoadPaymentUIRequest

@SuppressLint("SetJavaScriptEnabled")
class LoadPaymentUIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_payment_ui_activity)
        applyInsets(findViewById(R.id.main))

        val loadPaymentUIRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PortOne.REQUEST, LoadPaymentUIRequest::class.java)
        } else {
            intent.getParcelableExtra(PortOne.REQUEST)

        }
        val webView = findViewById<PortOneWebView>(R.id.web_view_load_payment_ui)

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

        if (loadPaymentUIRequest != null) {
            webView.loadPaymentUI(loadPaymentUIRequest, object : PaymentCallback {
                override fun onSuccess(response: PaymentResponse) {
                    setResult(PortOne.SUCCESS_CODE, Intent().putExtra(PortOne.RESPONSE, response))
                    finish()
                }

                override fun onFail(response: PaymentResponse) {
                    setResult(PortOne.FAIL_CODE, Intent().putExtra(PortOne.RESPONSE, response))
                    finish()
                }
            })
        }
    }
}