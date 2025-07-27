package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayBrandpayOptionsUiButtonStyle
import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayBrandpayOptionsUiLabels
import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayBrandpayOptionsUiNavigationBar

data class TossBrandpayBrandpayOptionsUi(
    /**
     * UI의 메인 색상. (기본값: #3182f6)
     */
    val highlightColor: String?,
    /**
     * 버튼 스타일
     * 
     * - default(기본값): 모서리가 둥글고 주변에 여백을 가진 버튼
     * - full: 하단 영역이 전부 채워지는 형태의 버튼
     */
    val buttonStyle: TossBrandpayBrandpayOptionsUiButtonStyle?,
    val labels: TossBrandpayBrandpayOptionsUiLabels?,
    val navigationBar: TossBrandpayBrandpayOptionsUiNavigationBar?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "highlightColor" to highlightColor?.let { highlightColor },
        "buttonStyle" to buttonStyle?.let { buttonStyle.toJson() },
        "labels" to labels?.let { labels.toJson() },
        "navigationBar" to navigationBar?.let { navigationBar.toJson() }
    )
}
