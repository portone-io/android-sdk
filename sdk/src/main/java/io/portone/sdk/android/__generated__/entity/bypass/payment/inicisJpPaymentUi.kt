package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 결제창 UI 설정
 */
data class InicisJpPaymentUI(
    /**
     * **결제창 색상**
     * 
     * 가능한 값:
     * 
     * - `red1`
     * - `red2`
     * - `red3`
     * - `red4`
     * - `orange`
     * - `yellow`
     * - `black`
     * - `purple`
     * - `green`
     * - `blue1`
     * - `blue2`
     * - `blue3`
     * - `blue4`
     * - `blue5`
     * - `blue6`
     */
    val colorTheme: String?,
    /**
     * **가맹점 로고 이미지 URL**
     * 
     * 69 \* 20 픽셀 크기의 이미지 URL
     */
    val logoUrl: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "colorTheme" to colorTheme?.let { colorTheme },
        "logoUrl" to logoUrl?.let { logoUrl }
    )
}
