package io.portone.sdk.__generated__.request

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.CardCompany
import kotlinx.parcelize.Parcelize

@Parcelize
data class IssueBillingKeyRequestUnionCard(
    /**
     * 카드 결제시 사용되는 카드사 코드
     */
    val cardCompany: CardCompany?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "cardCompany" to cardCompany?.let { cardCompany.toJson() }
    )
}
