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
import androidx.webkit.WebViewAssetLoader
import io.portone.sdk.android.identityverification.IdentityVerificationCallback
import io.portone.sdk.android.identityverification.IdentityVerificationJavascriptInterface
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyCallback
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyJavascriptInterface
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayCallback
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayJavascriptInterface
import io.portone.sdk.android.issuebillingkeyui.LoadIssueBillingKeyUIJavascriptInterface
import io.portone.sdk.android.payment.PaymentCallback
import io.portone.sdk.android.payment.PaymentJavascriptInterface
import io.portone.sdk.android.paymentui.LoadPaymentUIJavascriptInterface
import java.net.URISyntaxException
import io.portone.sdk.android.type.response.IdentityVerificationResponse
import io.portone.sdk.android.type.request.IdentityVerificationRequest
import io.portone.sdk.android.type.response.IssueBillingKeyResponse
import io.portone.sdk.android.type.request.IssueBillingKeyRequest
import io.portone.sdk.android.type.request.LoadIssueBillingKeyUIRequest
import io.portone.sdk.android.type.request.IssueBillingKeyAndPayRequest
import io.portone.sdk.android.type.response.IssueBillingKeyAndPayResponse
import io.portone.sdk.android.type.request.PaymentRequest
import io.portone.sdk.android.type.response.PaymentResponse
import io.portone.sdk.android.type.request.LoadPaymentUIRequest

@SuppressLint("SetJavaScriptEnabled")
class PortOneWebView(context: Context, attrs: AttributeSet? = null) : WebView(context, attrs) {
    // PortOne은 js sdk 인터페이스 object와 이름이 겹쳐 Portone으로 수정
    private val interfaceName = "Portone"
    private val defaultUrl = "https://appassets.androidplatform.net/assets/browser_sdk.html"
    private val loadUIUrl = "https://appassets.androidplatform.net/assets/browser_sdk_load_ui.html"
    private val assetLoader = WebViewAssetLoader.Builder()
        .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(context))
        .build()


    init {
        settings.run {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
        }
        webChromeClient = WebChromeClient()
    }

    fun requestPayment(paymentRequest: PaymentRequest, paymentCallback: PaymentCallback) {
        webViewClient = object : PortOneWebViewClientCompat(assetLoader) {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url == defaultUrl) {
                    view?.evaluateJavascript(
                        StringBuilder().append("javascript:PortOne.requestPayment(")
                            .append(paymentRequest.toJson().toMutableMap().apply { 
                                this["redirectUrl"] = PortOne.REDIRECT_URL 
                            }.toJsonString()).append(")")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.txId, error.paymentId, error.code, error.message, error.pgCode, error.pgMessage)")
                            .append("})")
                            .toString(),
                        null
                    )
                }
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val shouldOverride = if (request.url != null) {
                    val url = request.url
                    when (url.scheme) {
                        "market" -> {
                            view.context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    url
                                )
                            )
                            true
                        }

                        "intent", "nidlogin" -> {
                            view.context.startSchemeIntent(url.toString())
                        }

                        "portone" -> {
                            val result = handlePaymentResponse(url)
                            if (result.code == null) {
                                paymentCallback.onSuccess(result);
                            } else {
                                paymentCallback.onFail(result);
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

        addJavascriptInterface(object : PaymentJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String,
                txId: String?,
                paymentId: String?,
                code: String,
                message: String,
                pgCode: String?,
                pgMessage: String?
            ) {
                val fail = PaymentResponse(
                    transactionType = transactionType,
                    txId = txId.orEmpty(),
                    paymentId = paymentId.orEmpty(),
                    code = code,
                    message = message,
                    pgCode = pgCode,
                    pgMessage = pgMessage
                )
                paymentCallback.onFail(fail)
            }
        }, interfaceName)

        loadUrl("https://appassets.androidplatform.net/assets/browser_sdk.html")
    }

    fun requestIssueBillingKey(
        issueBillingKeyRequest: IssueBillingKeyRequest,
        issueBillingKeyCallback: IssueBillingKeyCallback
    ) {
        webViewClient = object : PortOneWebViewClientCompat(assetLoader) {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url == defaultUrl) {
                    view?.evaluateJavascript(
                        StringBuilder().append("javascript:PortOne.requestIssueBillingKey(")
                            .append(issueBillingKeyRequest.toJson().toMutableMap().apply { 
                                this["redirectUrl"] = PortOne.REDIRECT_URL 
                            }.toJsonString()).append(")")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.billingKey, error.code, error.message, error.pgCode, error.pgMessage)")
                            .append("})")
                            .toString(),
                        null
                    )
                }
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val shouldOverride = if (request.url != null) {
                    val url = request.url
                    when (url.scheme) {
                        "market" -> {
                            view.context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    url
                                )
                            )
                            true
                        }

                        "intent", "nidlogin" -> {
                            view.context.startSchemeIntent(url.toString())
                        }

                        "portone" -> {
                            val result = handleIssueBillingKeyResponse(url)
                            if (result.code == null) {
                                issueBillingKeyCallback.onSuccess(result);
                            } else {
                                issueBillingKeyCallback.onFail(result);
                            }
                            true
                        }

                        else -> {
                            // 삼성카드 백신 앱 onestore 대응
                            val requestedUrl = url.toString()
                            if (requestedUrl.startsWith("https://m.onestore") || requestedUrl.startsWith(
                                    "https://onesto.re"
                                )
                            ) {
                                context.startActivity(Intent(Intent.ACTION_VIEW, url))
                                true
                            } else {
                                false
                            }
                        }
                    }

                } else super.shouldOverrideUrlLoading(view, request)
                return shouldOverride

            }

        }

        addJavascriptInterface(object : IssueBillingKeyJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String,
                billingKey: String?,
                code: String,
                message: String,
                pgCode: String?,
                pgMessage: String?
            ) {
                val fail = IssueBillingKeyResponse(
                    transactionType = transactionType,
                    billingKey = billingKey.orEmpty(),
                    code = code,
                    message = message,
                    pgCode = pgCode,
                    pgMessage = pgMessage
                )
                issueBillingKeyCallback.onFail(fail)
            }
        }, interfaceName)

        loadUrl("https://appassets.androidplatform.net/assets/browser_sdk.html")
    }

    fun requestIdentityVerification(
        identityVerificationRequest: IdentityVerificationRequest,
        identityVerificationCallback: IdentityVerificationCallback
    ) {
        webViewClient = object : PortOneWebViewClientCompat(assetLoader) {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url == defaultUrl) {
                    view?.evaluateJavascript(
                        StringBuilder().append("javascript:PortOne.requestIdentityVerification(")
                            .append(identityVerificationRequest.toJson().toMutableMap().apply { 
                                this["redirectUrl"] = PortOne.REDIRECT_URL 
                            }.toJsonString()).append(")")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.identityVerificationTxId, error.identityVerificationId, error.code, error.message, error.pgCode, error.pgMessage)")
                            .append("})")
                            .toString(),
                        null
                    )
                }
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val shouldOverride = if (request.url != null) {
                    val url = request.url
                    when (url.scheme) {
                        "market" -> {
                            view.context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    url
                                )
                            )
                            true
                        }

                        "intent", "nidlogin" -> {
                            view.context.startSchemeIntent(url.toString())
                        }

                        "portone" -> {
                            val result = handleIdentityVerificationResponse(url)
                            if (result.code == null) {
                                identityVerificationCallback.onSuccess(result);
                            } else {
                                identityVerificationCallback.onFail(result);
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

        addJavascriptInterface(object : IdentityVerificationJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String,
                identityVerificationTxId: String?,
                identityVerificationId: String?,
                code: String,
                message: String,
                pgCode: String?,
                pgMessage: String?
            ) {
                val fail = IdentityVerificationResponse(
                    transactionType = transactionType,
                    identityVerificationId = identityVerificationId.orEmpty(),
                    identityVerificationTxId = identityVerificationTxId.orEmpty(),
                    code = code,
                    message = message,
                    pgCode = pgCode,
                    pgMessage = pgMessage
                )
                identityVerificationCallback.onFail(fail)
            }
        }, interfaceName)

        loadUrl("https://appassets.androidplatform.net/assets/browser_sdk.html")
    }

    fun requestIssueBillingKeyAndPay(
        issueBillingKeyAndPayRequest: IssueBillingKeyAndPayRequest,
        issueBillingKeyAndPayCallback: IssueBillingKeyAndPayCallback
    ) {
        webViewClient = object : PortOneWebViewClientCompat(assetLoader) {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url == defaultUrl) {
                    view?.evaluateJavascript(
                        StringBuilder().append("javascript:PortOne.requestIssueBillingKeyAndPay(")
                            .append(issueBillingKeyAndPayRequest.toJson().toMutableMap().apply { 
                                this["redirectUrl"] = PortOne.REDIRECT_URL 
                            }.toJsonString()).append(")")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.txId, error.paymentId, error.billingKey, error.code, error.message, error.pgCode, error.pgMessage)")
                            .append("})")
                            .toString(),
                        null
                    )
                }
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val shouldOverride = if (request.url != null) {
                    val url = request.url
                    when (url.scheme) {
                        "market" -> {
                            view.context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    url
                                )
                            )
                            true
                        }

                        "intent", "nidlogin" -> {
                            view.context.startSchemeIntent(url.toString())
                        }

                        "portone" -> {
                            val result = handleIssueBillingKeyAndPayResponse(url)
                            if (result.code == null) {
                                issueBillingKeyAndPayCallback.onSuccess(result);
                            } else {
                                issueBillingKeyAndPayCallback.onFail(result);
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

        addJavascriptInterface(object : IssueBillingKeyAndPayJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String,
                txId: String?,
                paymentId: String?,
                billingKey: String?,
                code: String,
                message: String,
                pgCode: String?,
                pgMessage: String?
            ) {
                val fail = IssueBillingKeyAndPayResponse(
                    transactionType = transactionType,
                    txId = txId.orEmpty(),
                    paymentId = paymentId.orEmpty(),
                    billingKey = billingKey.orEmpty(),
                    code = code,
                    message = message,
                    pgCode = pgCode,
                    pgMessage = pgMessage
                )
                issueBillingKeyAndPayCallback.onFail(fail)
            }
        }, interfaceName)

        loadUrl("https://appassets.androidplatform.net/assets/browser_sdk.html")
    }

    fun loadPaymentUI(
        loadPaymentUIRequest: LoadPaymentUIRequest,
        paymentCallback: PaymentCallback
    ) {
        webViewClient = object : PortOneWebViewClientCompat(assetLoader) {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url == loadUIUrl) {
                    view?.evaluateJavascript(
                        StringBuilder().append("javascript:PortOne.loadPaymentUI(")
                            .append(loadPaymentUIRequest.toJson().toMutableMap().apply { 
                                this["redirectUrl"] = PortOne.REDIRECT_URL 
                            }.toJsonString()).append(",{")
                            .append("onPaymentSuccess: (response) => { Portone.success(response.transactionType, response.txId, response.paymentId) },")
                            .append("onPaymentFail: (error) => { Portone.fail(error.transactionType, error.txId, error.paymentId, error.code, error.message, error.pgCode, error.pgMessage)}})")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.txId, error.paymentId, error.code, error.message, error.pgCode, error.pgMessage)")
                            .append("})")
                            .toString(),
                        null
                    )
                }
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val shouldOverride = if (request.url != null) {
                    val url = request.url
                    when (url.scheme) {
                        "intent", "nidlogin" -> {
                            view.context.startSchemeIntent(url.toString())
                        }

                        "portone" -> {
                            val result = handlePaymentResponse(url)
                            if (result.code == null) {
                                paymentCallback.onSuccess(result);
                            } else {
                                paymentCallback.onFail(result);
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

        addJavascriptInterface(object : LoadPaymentUIJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String,
                txId: String?,
                paymentId: String?,
                code: String,
                message: String,
                pgCode: String?,
                pgMessage: String?
            ) {
                val fail = PaymentResponse(
                    transactionType = transactionType,
                    txId = txId.orEmpty(),
                    paymentId = paymentId.orEmpty(),
                    code = code,
                    message = message,
                    pgCode = pgCode,
                    pgMessage = pgMessage
                )
                paymentCallback.onFail(fail)
            }

            @JavascriptInterface
            override fun success(
                transactionType: String,
                txId: String,
                paymentId: String,
            ) {
                val success = PaymentResponse(
                    transactionType = transactionType,
                    txId = txId,
                    paymentId = paymentId,
                    code = null,
                    message = null,
                    pgCode = null,
                    pgMessage = null
                )
                paymentCallback.onSuccess(success)
            }
        }, interfaceName)

        loadUrl("https://appassets.androidplatform.net/assets/browser_sdk_load_ui.html")
    }

    fun loadIssueBillingKeyUI(
        loadIssueBillingKeyUIRequest: LoadIssueBillingKeyUIRequest,
        issueBillingKeyCallback: IssueBillingKeyCallback
    ) {

        webViewClient = object : PortOneWebViewClientCompat(assetLoader) {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url == loadUIUrl) {
                    view?.evaluateJavascript(
                        StringBuilder().append("javascript:PortOne.loadIssueBillingKeyUI(")
                            .append(loadIssueBillingKeyUIRequest.toJson().toMutableMap().apply { 
                                this["redirectUrl"] = PortOne.REDIRECT_URL 
                            }.toJsonString()).append(",{")
                            .append("onIssueBillingKeySuccess: (response) => { Portone.success(response.transactionType, response.billingKey) },")
                            .append("onIssueBillingKeyFail: (error) => { Portone.fail(error.transactionType, error.billingKey, error.code, error.message, error.pgCode, error.pgMessage)}})")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.billingKey, error.code, error.message, error.pgCode, error.pgMessage)")
                            .append("})")
                            .toString(),
                        null
                    )
                }
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val shouldOverride = if (request.url != null) {
                    val url = request.url
                    when (url.scheme) {
                        "intent", "nidlogin" -> {
                            view.context.startSchemeIntent(url.toString())
                        }

                        "portone" -> {
                            val result = handleIssueBillingKeyResponse(url)
                            if (result.code == null) {
                                issueBillingKeyCallback.onSuccess(result);
                            } else {
                                issueBillingKeyCallback.onFail(result);
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

        addJavascriptInterface(object : LoadIssueBillingKeyUIJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String,
                billingKey: String?,
                code: String,
                message: String,
                pgCode: String?,
                pgMessage: String?
            ) {
                val fail = IssueBillingKeyResponse(
                    transactionType = transactionType,
                    billingKey = billingKey.orEmpty(),
                    code = code,
                    message = message,
                    pgCode = pgCode,
                    pgMessage = pgMessage
                )
                issueBillingKeyCallback.onFail(fail)
            }

            @JavascriptInterface
            override fun success(
                transactionType: String,
                billingKey: String,
            ) {
                val success = IssueBillingKeyResponse(
                    transactionType = transactionType,
                    billingKey = billingKey,
                    code = null,
                    message = null,
                    pgCode = null,
                    pgMessage = null
                )
                issueBillingKeyCallback.onSuccess(success)
            }
        }, interfaceName)

        loadUrl("https://appassets.androidplatform.net/assets/browser_sdk_load_ui.html")
    }

    private fun handlePaymentResponse(responseUrl: Uri): PaymentResponse {
        return PaymentResponse(
            transactionType = responseUrl.getQueryParameter(
                "transactionType"
            ).orEmpty(),
            txId = responseUrl.getQueryParameter("txId").orEmpty(),
            paymentId = responseUrl.getQueryParameter("paymentId").orEmpty(),
            code = responseUrl.getQueryParameter("code"),
            message = responseUrl.getQueryParameter("message"),
            pgCode = responseUrl.getQueryParameter("pgCode"),
            pgMessage = responseUrl.getQueryParameter("pgMessage")
        )
    }

    private fun handleIssueBillingKeyResponse(responseUrl: Uri): IssueBillingKeyResponse {
        return IssueBillingKeyResponse(
            transactionType = responseUrl.getQueryParameter(
                "transactionType"
            ).orEmpty(),
            billingKey = responseUrl.getQueryParameter("billingKey")
                .orEmpty(),
            code = responseUrl.getQueryParameter("code"),
            message = responseUrl.getQueryParameter("message"),
            pgCode = responseUrl.getQueryParameter("pgCode"),
            pgMessage = responseUrl.getQueryParameter("pgMessage")
        )
    }

    private fun handleIdentityVerificationResponse(responseUrl: Uri): IdentityVerificationResponse {
        return IdentityVerificationResponse(
            transactionType = responseUrl.getQueryParameter(
                "transactionType"
            ).orEmpty(),
            identityVerificationId = responseUrl.getQueryParameter(
                "identityVerificationId"
            )
                .orEmpty(),
            identityVerificationTxId = responseUrl.getQueryParameter(
                "identityVerificationTxId"
            )
                .orEmpty(),
            code = responseUrl.getQueryParameter("code"),
            message = responseUrl.getQueryParameter("message"),
            pgCode = responseUrl.getQueryParameter("pgCode"),
            pgMessage = responseUrl.getQueryParameter("pgMessage")
        )
    }

    private fun handleIssueBillingKeyAndPayResponse(responseUrl: Uri): IssueBillingKeyAndPayResponse {
        return IssueBillingKeyAndPayResponse(
            transactionType = responseUrl.getQueryParameter(
                "transactionType"
            ).orEmpty(),
            txId = responseUrl.getQueryParameter("txId")
                .orEmpty(),
            paymentId = responseUrl.getQueryParameter("paymentId")
                .orEmpty(),
            billingKey = responseUrl.getQueryParameter("billingKey")
                .orEmpty(),
            code = responseUrl.getQueryParameter("code"),
            message = responseUrl.getQueryParameter("message"),
            pgCode = responseUrl.getQueryParameter("pgCode"),
            pgMessage = responseUrl.getQueryParameter("pgMessage")
        )
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
