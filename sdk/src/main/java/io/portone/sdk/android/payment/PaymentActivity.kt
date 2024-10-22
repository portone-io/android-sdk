package io.portone.sdk.android.payment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.PortOneWebView
import io.portone.sdk.android.R

@SuppressLint("SetJavaScriptEnabled")
class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        val paymentRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PortOne.REQUEST, PaymentRequest::class.java)
        } else {
            intent.getParcelableExtra(PortOne.REQUEST)

        }
        val webView = findViewById<PortOneWebView>(R.id.web_view_payment)
        if (paymentRequest != null) {
            webView.requestPayment(paymentRequest, object : PaymentCallback {
                override fun onSuccess(response: PaymentResponse.Success) {
                    setResult(PortOne.SUCCESS_CODE, Intent().putExtra(PortOne.RESPONSE, response))
                    finish()
                }

                override fun onFail(response: PaymentResponse.Fail) {
                    setResult(PortOne.FAIL_CODE, Intent().putExtra(PortOne.RESPONSE, response))
                    finish()
                }
            })
        }
    }
}