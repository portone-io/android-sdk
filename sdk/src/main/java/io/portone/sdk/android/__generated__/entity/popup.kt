package io.portone.sdk.__generated__.entity

/**
 * **팝업 관련 필드**
 * 
 * UI가 팝업 창으로 열릴 때 적용되는 속성입니다.
 */
data class Popup(
    /**
     * **팝업 정중앙 표시**
     * `true`로 설정하면 팝업이 브라우저 화면의 정중앙에 표시됩니다. 결제사 및 환경에 따라 적용되지 않을 수 있습니다.
     */
    val center: Boolean?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "center" to center?.let { center }
    )
}
