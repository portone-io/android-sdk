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
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyRequest
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyResponse
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayCallback
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayJavascriptInterface
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayRequest
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayResponse
import io.portone.sdk.android.issuebillingkeyui.LoadIssueBillingKeyUIJavascriptInterface
import io.portone.sdk.android.issuebillingkeyui.LoadIssueBillingKeyUIRequest
import io.portone.sdk.android.payment.PaymentCallback
import io.portone.sdk.android.payment.PaymentJavascriptInterface
import io.portone.sdk.android.payment.PaymentRequest
import io.portone.sdk.android.payment.PaymentResponse
import io.portone.sdk.android.paymentui.LoadPaymentUIJavascriptInterface
import io.portone.sdk.android.paymentui.LoadPaymentUIRequest
import kotlinx.serialization.encodeToString
import java.net.URISyntaxException
import io.portone.sdk.__generated__.response.IdentityVerificationResponse
import io.portone.sdk.__generated__.request.IdentityVerificationRequest

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
                            .append("${encodingformat.encodeToString(paymentRequest.toInternal())})")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.txId, error.paymentId, error.code, error.message)")
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

        addJavascriptInterface(object : PaymentJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String?,
                txId: String?,
                paymentId: String?,
                code: String,
                message: String,
                pgCode: String?,
                pgMessage: String?,
            ) {
                val fail = PaymentResponse.Fail(
                    transactionType?.let { TransactionType.valueOf(it) },
                    txId,
                    paymentId,
                    code,
                    message,
                    pgCode,
                    pgMessage,
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
                            .append("${encodingformat.encodeToString(issueBillingKeyRequest.toInternal())})")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.billingKey, error.code, error.message)")
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
                transactionType: String?,
                billingKey: String?,
                code: String,
                message: String,
                pgCode: String?,
                pgMessage: String?
            ) {
                val fail = IssueBillingKeyResponse.Fail(
                    transactionType?.let { TransactionType.valueOf(it) },
                    billingKey,
                    code,
                    message,
                    pgCode,
                    pgMessage,
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
                            .append(encodingformat.encodeToString(identityVerificationRequest.toJson()))
                            .append(")")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.identityVerificationTxId, error.code, error.message)")
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
                                identityVerificationCallback.onFail(result);
                            } else {
                                identityVerificationCallback.onSuccess(result);
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
                identityVerificationTxId: String,
                identityVerificationId: String,
                code: String?,
                message: String?,
                pgCode: String?,
                pgMessage: String?
            ) {
                val fail = IdentityVerificationResponse(
                    transactionType = transactionType,
                    identityVerificationId = identityVerificationId,
                    identityVerificationTxId = identityVerificationTxId,
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
                            .append("${encodingformat.encodeToString(issueBillingKeyAndPayRequest.toInternal())})")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.txId, error.paymentId, error.billingKey, error.code, error.message)")
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

        addJavascriptInterface(object : IssueBillingKeyAndPayJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String?,
                txId: String?,
                paymentId: String?,
                billingKey: String?,
                code: String,
                message: String,
                pgCode: String?,
                pgMessage: String?
            ) {
                val fail = IssueBillingKeyAndPayResponse.Fail(
                    transactionType?.let { TransactionType.valueOf(it) },
                    txId,
                    paymentId,
                    billingKey,
                    code,
                    message,
                    pgCode,
                    pgMessage
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

        addJavascriptInterface(object : LoadPaymentUIJavascriptInterface {
            @JavascriptInterface
            override fun fail(
                transactionType: String?,
                txId: String?,
                paymentId: String?,
                code: String,
                message: String,
                pgCode: String?,
                pgMessage: String?,
            ) {
                val fail = PaymentResponse.Fail(
                    transactionType?.let { TransactionType.valueOf(it) },
                    txId,
                    paymentId,
                    code,
                    message,
                    pgCode,
                    pgMessage,
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
                            .append("${encodingformat.encodeToString(loadIssueBillingKeyUIRequest.toInternal())},{")
                            .append("onIssueBillingKeySuccess: (response) => { Portone.success(response.transactionType, response.billingKey) },")
                            .append("onIssueBillingKeyFail: (error) => { Portone.fail(error.transactionType, error.billingKey, error.code, error.message)}})")
                            .append(".catch(function(error){")
                            .append("Portone.fail(error.transactionType, error.billingKey, error.code, error.message)")
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

        addJavascriptInterface(object : LoadIssueBillingKeyUIJavascriptInterface {
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
                    message,
                    pgCode = null,
                    pgMessage = null,
                )
                issueBillingKeyCallback.onFail(fail)
            }

            @JavascriptInterface
            override fun success(
                transactionType: String,
                billingKey: String,
            ) {
                val success = IssueBillingKeyResponse.Success(
                    transactionType.let { TransactionType.valueOf(it) },
                    billingKey,
                )
                issueBillingKeyCallback.onSuccess(success)
            }
        }, interfaceName)

        loadUrl("https://appassets.androidplatform.net/assets/browser_sdk_load_ui.html")
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
                message = responseUrl.getQueryParameter(PaymentResponse.MESSAGE).orEmpty(),
                pgCode = responseUrl.getQueryParameter(PaymentResponse.PG_CODE).orEmpty(),
                pgMessage = responseUrl.getQueryParameter(PaymentResponse.PG_MESSAGE).orEmpty()
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
                message = responseUrl.getQueryParameter(IssueBillingKeyResponse.MESSAGE).orEmpty(),
                pgCode = responseUrl.getQueryParameter(IssueBillingKeyResponse.PG_CODE).orEmpty(),
                pgMessage = responseUrl.getQueryParameter(IssueBillingKeyResponse.PG_MESSAGE)
                    .orEmpty(),
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
            pgCode = responseUrl . getQueryParameter ("pgCode"),
            pgMessage = responseUrl.getQueryParameter("pgMessage")
        )
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
                    .orEmpty(),
                pgCode = responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.PG_CODE)
                    .orEmpty(),
                pgMessage = responseUrl.getQueryParameter(IssueBillingKeyAndPayResponse.PG_MESSAGE)
                    .orEmpty(),
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
