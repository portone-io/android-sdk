package io.portone.sdk.__generated__.entity.bypass.payment

data class TossBrandpayBrandpayOptionsUiNavigationBar(
    /**
     * 내비게이션 바 사용 여부. (기본값: true)
     */
    val visible: Boolean?,
    /**
     * 내비게이션 바 위쪽에 설정할 여백 값. 값의 단위는 px
     */
    val paddingTop: Long?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "visible" to visible?.let { visible },
        "paddingTop" to paddingTop?.let { paddingTop }
    )
}
