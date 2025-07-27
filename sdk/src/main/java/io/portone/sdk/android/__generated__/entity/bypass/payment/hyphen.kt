package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 하이픈 bypass 파라미터
 */
@Parcelize
data class HyphenBypass(
    val designCd: String?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "designCd" to designCd?.let { designCd }
    )
}
