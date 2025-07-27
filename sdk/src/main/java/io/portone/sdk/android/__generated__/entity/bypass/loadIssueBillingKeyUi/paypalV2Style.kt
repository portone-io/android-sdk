package io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI

import io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI.PaypalV2StyleColor
import io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI.PaypalV2StyleLabel
import io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI.PaypalV2StyleLayout
import io.portone.sdk.__generated__.entity.bypass.loadIssueBillingKeyUI.PaypalV2StyleShape

/**
 * 페이팔 빌링키 발급 UI 호출 시 필요한 파라미터
 */
data class PaypalV2Style(
    /**
     * 버튼 색상
     */
    val color: PaypalV2StyleColor?,
    /**
     * 버튼 높이
     */
    val height: Long?,
    /**
     * 버튼 라벨
     */
    val label: PaypalV2StyleLabel?,
    /**
     * 버튼 렌더링 방향
     */
    val layout: PaypalV2StyleLayout?,
    /**
     * 버튼 모양
     */
    val shape: PaypalV2StyleShape?,
    /**
     * 버튼 하위에 문구 노출 여부
     */
    val tagline: Boolean?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "color" to color?.let { color.toJson() },
        "height" to height?.let { height },
        "label" to label?.let { label.toJson() },
        "layout" to layout?.let { layout.toJson() },
        "shape" to shape?.let { shape.toJson() },
        "tagline" to tagline?.let { tagline }
    )
}
