package io.portone.sdk.__generated__.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 상점 영업시간 (HH:mm)
 */
@Parcelize
data class StoreDetailsOpeningHours(
    /**
     * 영업 시작 시간
     */
    val `open`: String?,
    /**
     * 영업 종료 시간
     */
    val close: String?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "open" to `open`?.let { `open` },
        "close" to close?.let { close }
    )
}
