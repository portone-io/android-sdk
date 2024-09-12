package io.portone.sdk.android

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.serialization.encodeToString
import java.net.URISyntaxException

@SuppressLint("SetJavaScriptEnabled")
class PortOneWebView(context: Context, attrs: AttributeSet? = null) : WebView(context, attrs) {
    // PortOne은 js sdk 인터페이스 object와 이름이 겹쳐 Portone으로 수정
    val interfaceName = "Portone"

    init {
        settings.run {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
        }

        webChromeClient = WebChromeClient()
    }

    fun requestPayment(paymentRequest: PaymentRequest, paymentCallback: PaymentCallback) {
        loadUrl("file:///android_asset/browser_sdk.html")

        addJavascriptInterface(object : PaymentJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String?,
                txId: String?,
                paymentId: String?,
                code: String,
                message: String
            ) {
                val fail = PaymentResponse.Fail(
                    transactionType?.let { TransactionType.valueOf(it) },
                    txId,
                    paymentId,
                    code,
                    message
                )
                paymentCallback.onFail(fail)
            }
        }, interfaceName)

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.evaluateJavascript(
                    StringBuilder().append("javascript:PortOne.requestPayment(")
                        .append("${encodingformat.encodeToString(paymentRequest.toInternal())})")
                        .append(".catch(function(error){")
                        .append("Portone.fail(error.transactionType, error.txId, error.paymentId, error.code, error.message)")
                        .append("})")
                        .toString(),
                    null
                )
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val shouldOverride = if (view != null && request?.url != null) {
                    val url = request.url
                    when (url.scheme) {
                        "intent" -> {
                            view.context.startSchemeIntent(url.toString())
                        }

                        "portone" -> {
                            when (val result = handlePaymentResponse(url)) {
                                is PaymentResponse.Fail -> paymentCallback.onFail(result)
                                is PaymentResponse.Success -> paymentCallback.onSuccess(result)
                            }
                            true
                        }

                        else -> {
                            false
                        }
                    }

                } else super.shouldOverrideUrlLoading(view, request)
                return shouldOverride

            }

        }
    }

    fun requestIssueBillingKey(
        issueBillingKeyRequest: IssueBillingKeyRequest,
        issueBillingKeyCallback: IssueBillingKeyCallback
    ) {
        loadUrl("file:///android_asset/browser_sdk.html")

        addJavascriptInterface(object : IssueBillingKeyJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String?,
                billingKey: String?,
                code: String,
                message: String
            ) {
                val fail = IssueBillingKeyResponse.Fail(
                    transactionType?.let { TransactionType.valueOf(it) },
                    billingKey,
                    code,
                    message
                )
                issueBillingKeyCallback.onFail(fail)
            }
        }, interfaceName)

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.evaluateJavascript(
                    StringBuilder().append("javascript:PortOne.requestIssueBillingKey(")
                        .append("${encodingformat.encodeToString(issueBillingKeyRequest.toInternal())})")
                        .append(".catch(function(error){")
                        .append("Portone.fail(error.transactionType, error.billingKey, error.code, error.message)")
                        .append("})")
                        .toString(),
                    null
                )
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val shouldOverride = if (view != null && request?.url != null) {
                    val url = request.url
                    when (url.scheme) {
                        "intent" -> {
                            view.context.startSchemeIntent(url.toString())
                        }

                        "portone" -> {
                            when (val result = handleIssueBillingKeyResponse(url)) {
                                is IssueBillingKeyResponse.Fail -> issueBillingKeyCallback.onFail(
                                    result
                                )

                                is IssueBillingKeyResponse.Success -> issueBillingKeyCallback.onSuccess(
                                    result
                                )
                            }
                            true
                        }

                        else -> {
                            false
                        }
                    }

                } else super.shouldOverrideUrlLoading(view, request)
                return shouldOverride

            }

        }
    }
    private fun handlePaymentResponse(responseUrl: Uri): PaymentResponse {
        return if (responseUrl.getQueryParameter(PaymentResponse.CODE) != null) {
            PaymentResponse.Fail(
                transactionType = TransactionType.valueOf(
                    responseUrl.getQueryParameter(
                        PaymentResponse.TRANSACTION_TYPE
                    ).orEmpty()
                ),
                txId = responseUrl.getQueryParameter(PaymentResponse.TX_ID).orEmpty(),
                paymentId = responseUrl.getQueryParameter(PaymentResponse.PAYMENT_ID).orEmpty(),
                code = responseUrl.getQueryParameter(PaymentResponse.CODE).orEmpty(),
                message = responseUrl.getQueryParameter(PaymentResponse.MESSAGE).orEmpty()
            )
        } else {
            PaymentResponse.Success(
                transactionType = TransactionType.valueOf(
                    responseUrl.getQueryParameter(
                        PaymentResponse.TRANSACTION_TYPE
                    ).orEmpty()
                ),
                txId = responseUrl.getQueryParameter(PaymentResponse.TX_ID).orEmpty(),
                paymentId = responseUrl.getQueryParameter(PaymentResponse.PAYMENT_ID).orEmpty()
            )
        }

    }

    private fun handleIssueBillingKeyResponse(responseUrl: Uri): IssueBillingKeyResponse {
        return if (responseUrl.getQueryParameter(IssueBillingKeyResponse.CODE) != null) {
            IssueBillingKeyResponse.Fail(
                transactionType = TransactionType.valueOf(
                    responseUrl.getQueryParameter(
                        IssueBillingKeyResponse.TRANSACTION_TYPE
                    ).orEmpty()
                ),
                billingKey = responseUrl.getQueryParameter(IssueBillingKeyResponse.BILLING_KEY)
                    .orEmpty(),
                code = responseUrl.getQueryParameter(IssueBillingKeyResponse.CODE).orEmpty(),
                message = responseUrl.getQueryParameter(IssueBillingKeyResponse.MESSAGE).orEmpty()
            )
        } else {
            IssueBillingKeyResponse.Success(
                transactionType = TransactionType.valueOf(
                    responseUrl.getQueryParameter(
                        IssueBillingKeyResponse.TRANSACTION_TYPE
                    ).orEmpty()
                ),
                billingKey = responseUrl.getQueryParameter(IssueBillingKeyResponse.BILLING_KEY)
                    .orEmpty(),
            )
        }

    }

    private fun Context.startSchemeIntent(url: String): Boolean {
        val schemeIntent: Intent = try {
            Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
        } catch (e: URISyntaxException) {
            return false
        }
        try {
            startActivity(schemeIntent)
            return true
        } catch (e: ActivityNotFoundException) {
            val packageName = schemeIntent.getPackage()
            if (!packageName.isNullOrBlank()) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$packageName")
                    )
                )
                return true
            }
        }
        return false
    }
}
