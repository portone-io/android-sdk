package io.portone.sdk.__generated__.entity

/**
 * 상점 영업시간 (HH:mm)
 */
data class StoreDetailsOpeningHours(
    /**
     * 영업 시작 시간
     */
    val `open`: String?,
    /**
     * 영업 종료 시간
     */
    val close: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "open" to `open`?.let { `open` },
        "close" to close?.let { close }
    )
}
