package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.issueBillingKey.InicisV2CardUse
import kotlinx.parcelize.Parcelize

/**
 * **KG이니시스 bypass 파라미터**
 */
@Parcelize
data class InicisV2IssueBillingKeyBypass(
    /**
     * 개인/법인카드 선택 옵션
     */
    val carduse: InicisV2CardUse?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "carduse" to carduse?.let { carduse.toJson() }
    )
}
