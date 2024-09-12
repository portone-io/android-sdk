package io.portone.sdk.android

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant as KInstant
import java.time.Instant

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
        Clock.System.now()
        Instant.now()
        KInstant.DISTANT_PAST
        KInstant.fromEpochSeconds(1000,1)
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