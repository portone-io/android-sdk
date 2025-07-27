package io.portone.sdk.__generated__.entity.bypass.payment

data class TossBrandpayBrandpayOptionsUiLabels(
    /**
     * UI에 표시되는 원터치 결제를 대신해 사용할 텍스트. (기본값: "원터치 결제")
     */
    val oneTouchPay: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "oneTouchPay" to oneTouchPay?.let { oneTouchPay }
    )
}
