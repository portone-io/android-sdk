package io.portone.sdk.__generated__.request

import io.portone.sdk.__generated__.entity.CardCompany

data class IssueBillingKeyRequestUnionCard(
    /**
     * 카드 결제시 사용되는 카드사 코드
     */
    val cardCompany: CardCompany?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "cardCompany" to cardCompany?.let { cardCompany.toJson() }
    )
}
