package io.portone.sdk.android

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat

internal open class PortOneWebViewClientCompat(private val assetLoader: WebViewAssetLoader) : WebViewClientCompat() {
    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        return request?.url?.let {
            assetLoader.shouldInterceptRequest(it)
        } ?: super.shouldInterceptRequest(view, request)
    }
}