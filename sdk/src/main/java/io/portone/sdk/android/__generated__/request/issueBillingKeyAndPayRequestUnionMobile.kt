package io.portone.sdk.__generated__.request

import io.portone.sdk.__generated__.entity.Carrier

data class IssueBillingKeyAndPayRequestUnionMobile(
    /**
     * 통신사 코드
     */
    val carrier: Carrier?,
    val avaliableCarriers: List<Carrier>?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "carrier" to carrier?.let { carrier.toJson() },
        "avaliableCarriers" to avaliableCarriers?.let { avaliableCarriers.map { it.toJson() } }
    )
}
