package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.NaverpayProductItem
import io.portone.sdk.__generated__.entity.bypass.payment.NaverpaySubMerchantInfo

/**
 * 네이버페이 bypass 파라미터
 */
data class NaverpayPaymentBypass(
    /**
     * 이용 완료일(YYYYMMDD)
     */
    val useCfmYmdt: String?,
    val productItems: List<NaverpayProductItem>,
    /**
     * 하부 가맹점 정보. PG 업종 가맹점인 경우에만 필수 값
     */
    val subMerchantInfo: NaverpaySubMerchantInfo?,
    /**
     * 배송비
     */
    val deliveryFee: Long?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "useCfmYmdt" to useCfmYmdt?.let { useCfmYmdt },
        "productItems" to productItems.map { it.toJson() },
        "subMerchantInfo" to subMerchantInfo?.let { subMerchantInfo.toJson() },
        "deliveryFee" to deliveryFee?.let { deliveryFee }
    )
}
