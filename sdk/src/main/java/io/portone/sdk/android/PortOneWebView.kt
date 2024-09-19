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
import io.portone.sdk.android.identityverification.IdentityVerificationCallback
import io.portone.sdk.android.identityverification.IdentityVerificationJavascriptInterface
import io.portone.sdk.android.identityverification.IdentityVerificationRequest
import io.portone.sdk.android.identityverification.IdentityVerificationResponse
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyCallback
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyJavascriptInterface
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyRequest
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyResponse
import io.portone.sdk.android.payment.PaymentCallback
import io.portone.sdk.android.payment.PaymentJavascriptInterface
import io.portone.sdk.android.payment.PaymentRequest
import io.portone.sdk.android.payment.PaymentResponse
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayCallback
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayJavascriptInterface
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayRequest
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayResponse
import io.portone.sdk.android.paymentui.LoadPaymentUIJavascriptInterface
import io.portone.sdk.android.paymentui.LoadPaymentUIRequest
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

    fun requestIdentityVerification(
        identityVerificationRequest: IdentityVerificationRequest,
        identityVerificationCallback: IdentityVerificationCallback
    ) {
        loadUrl("file:///android_asset/browser_sdk.html")

        addJavascriptInterface(object : IdentityVerificationJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String?,
                identityVerificationTxId: String?,
                code: String,
                message: String
            ) {
                val fail = IdentityVerificationResponse.Fail(
                    transactionType?.let { TransactionType.valueOf(it) },
                    identityVerificationTxId,
                    code,
                    message
                )
                identityVerificationCallback.onFail(fail)
            }
        }, interfaceName)

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.evaluateJavascript(
                    StringBuilder().append("javascript:PortOne.requestIdentityVerification(")
                        .append("${encodingformat.encodeToString(identityVerificationRequest.toInternal())})")
                        .append(".catch(function(error){")
                        .append("Portone.fail(error.transactionType, error.identityVerificationTxId, error.code, error.message)")
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
                            when (val result = handleIdentityVerificationResponse(url)) {
                                is IdentityVerificationResponse.Fail -> identityVerificationCallback.onFail(
                                    result
                                )

                                is IdentityVerificationResponse.Success -> identityVerificationCallback.onSuccess(
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

    fun requestIssueBillingKeyAndPay(
        issueBillingKeyAndPayRequest: IssueBillingKeyAndPayRequest,
        issueBillingKeyAndPayCallback: IssueBillingKeyAndPayCallback
    ) {
        loadUrl("file:///android_asset/browser_sdk.html")

        addJavascriptInterface(object : IssueBillingKeyAndPayJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String?,
                txId: String?,
                paymentId: String?,
                billingKey: String?,
                code: String,
                message: String
            ) {
                val fail = IssueBillingKeyAndPayResponse.Fail(
                    transactionType?.let { TransactionType.valueOf(it) },
                    txId,
                    paymentId,
                    billingKey,
                    code,
                    message
                )
                issueBillingKeyAndPayCallback.onFail(fail)
            }
        }, interfaceName)

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.evaluateJavascript(
                    StringBuilder().append("javascript:PortOne.requestIssueBillingKeyAndPay(")
                        .append("${encodingformat.encodeToString(issueBillingKeyAndPayRequest.toInternal())})")
                        .append(".catch(function(error){")
                        .append("Portone.fail(error.transactionType, error.txId, error.paymentId, error.billingKey, error.code, error.message)")
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
                            when (val result = handleIssueBillingKeyAndPayResponse(url)) {
                                is IssueBillingKeyAndPayResponse.Fail -> issueBillingKeyAndPayCallback.onFail(
                                    result
                                )

                                is IssueBillingKeyAndPayResponse.Success -> issueBillingKeyAndPayCallback.onSuccess(
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

    fun loadPaymentUI(
        loadPaymentUIRequest: LoadPaymentUIRequest,
        paymentCallback: PaymentCallback
    ) {
        loadUrl("file:///android_asset/browser_sdk_load_ui.html")

        addJavascriptInterface(object : LoadPaymentUIJavascriptInterface {
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
            @JavascriptInterface
            override fun success(
                transactionType: String,
                txId: String,
                paymentId: String,
            ) {
                val success = PaymentResponse.Success(
                    transactionType.let { TransactionType.valueOf(it) },
                    txId,
                    paymentId,
                )
                paymentCallback.onSuccess(success)
            }
        }, interfaceName)

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.evaluateJavascript(
                    StringBuilder().append("javascript:PortOne.loadPaymentUI(")
                        .append("${encodingformat.encodeToString(loadPaymentUIRequest.toInternal())},{")
                        .append("onPaymentSuccess: (response) => { Portone.success(response.transactionType, response.txId, response.paymentId) },")
                        .append("onPaymentFail: (error) => { Portone.fail(error.transactionType, error.txId, error.paymentId, error.code, error.message)}})")
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
                                is PaymentResponse.Fail -> paymentCallback.onFail(
                                    result
                                )

                                is PaymentResponse.Success -> paymentCallback.onSuccess(
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

    private fun handleIdentityVerificationResponse(responseUrl: Uri): IdentityVerificationResponse {
        return if (responseUrl.getQueryParameter(IdentityVerificationResponse.CODE) != null) {
            IdentityVerificationResponse.Fail(
                transactionType = TransactionType.valueOf(
                    responseUrl.getQueryParameter(
                        IssueBillingKeyResponse.TRANSACTION_TYPE
                    ).orEmpty()
                ),
                identityVerificationTxId = responseUrl.getQueryParameter(
                    IdentityVerificationResponse.IDENTITY_VERIFICATION_TX_ID
                )
                    .orEmpty(),
                code = responseUrl.getQueryParameter(IdentityVerificationResponse.CODE).orEmpty(),
                message = responseUrl.getQueryParameter(IdentityVerificationResponse.MESSAGE)
                    .orEmpty()
            )
        } else {
            IdentityVerificationResponse.Success(
                transactionType = TransactionType.valueOf(
                    responseUrl.getQueryParameter(
                        IdentityVerificationResponse.TRANSACTION_TYPE
                    ).orEmpty()
                ),
                identityVerificationTxId = responseUrl.getQueryParameter(
                    IdentityVerificationResponse.IDENTITY_VERIFICATION_TX_ID
                )
                    .orEmpty(),
                identityVerificationId = responseUrl.getQueryParameter(IdentityVerificationResponse.IDENTITY_VERIFICATION_ID)
                    .orEmpty(),
            )
        }

    }

    private fun handleIssueBillingKeyAndPayResponse(responseUrl: Uri): IssueBillingKeyAndPayResponse {
        return if (responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.CODE) != null) {
            IssueBillingKeyAndPayResponse.Fail(
                transactionType = TransactionType.valueOf(
                    responseUrl.getQueryParameter(
                        IssueBillingKeyAndPayResponse.TRANSACTION_TYPE
                    ).orEmpty()
                ),
                billingKey = responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.BILLING_KEY)
                    .orEmpty(),
                paymentId = responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.PAYMENT_ID)
                    .orEmpty(),
                txId = responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.TX_ID)
                    .orEmpty(),
                code = responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.CODE).orEmpty(),
                message = responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.MESSAGE)
                    .orEmpty()
            )
        } else {
            IssueBillingKeyAndPayResponse.Success(
                transactionType = TransactionType.valueOf(
                    responseUrl.getQueryParameter(
                        IssueBillingKeyAndPayResponse.TRANSACTION_TYPE
                    ).orEmpty()
                ),
                billingKey = responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.BILLING_KEY)
                    .orEmpty(),
                paymentId = responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.PAYMENT_ID)
                    .orEmpty(),
                txId = responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.TX_ID)
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
