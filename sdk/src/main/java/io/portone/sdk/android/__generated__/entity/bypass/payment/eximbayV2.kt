package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2BillTo
import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2Merchant
import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2Payment
import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2Settings
import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2ShipTo
import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2Surcharge
import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2Tax

/**
 * 엑심베이 V2 bypass 파라미터
 */
data class EximbayV2Bypass(
    /**
     * 결제 정보
     */
    val payment: EximbayV2Payment?,
    /**
     * 상점 정보
     */
    val merchant: EximbayV2Merchant?,
    /**
     * 세금 정보
     */
    val tax: EximbayV2Tax?,
    /**
     * 최대 3개의 추가 비용 목록
     */
    val surcharge: List<EximbayV2Surcharge>?,
    /**
     * 배송지 정보
     */
    val shipTo: EximbayV2ShipTo?,
    /**
     * 청구지 정보
     */
    val billTo: EximbayV2BillTo?,
    /**
     * 설정 정보
     */
    val settings: EximbayV2Settings?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "payment" to payment?.let { payment.toJson() },
        "merchant" to merchant?.let { merchant.toJson() },
        "tax" to tax?.let { tax.toJson() },
        "surcharge" to surcharge?.let { surcharge.map { it.toJson() } },
        "ship_to" to shipTo?.let { shipTo.toJson() },
        "bill_to" to billTo?.let { billTo.toJson() },
        "settings" to settings?.let { settings.toJson() }
    )
}
