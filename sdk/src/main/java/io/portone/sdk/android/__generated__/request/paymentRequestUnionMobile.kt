package io.portone.sdk.__generated__.request

import io.portone.sdk.__generated__.entity.Carrier

/**
 * **휴대전화 결제 설정**
 */
data class PaymentRequestUnionMobile(
    /**
     * 통신사 코드
     */
    val carrier: Carrier?,
    /**
     * **일부 통신사만 노출 설정**
     * 
     * 일부 통신사만을 선택 가능하게 하고 싶은 경우 사용하는 옵션입니다.
     */
    val avaliableCarriers: List<Carrier>?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "carrier" to carrier?.let { carrier.toJson() },
        "avaliableCarriers" to avaliableCarriers?.let { avaliableCarriers.map { it.toJson() } }
    )
}
