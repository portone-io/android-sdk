package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.NaverpayProductItemPayReferrer

data class NaverpayProductItem(
    /**
     * 결제 상품 유형
     */
    val categoryType: String,
    /**
     * 결제 상품 분류
     */
    val categoryId: String,
    /**
     * 결제 상품 식별값
     */
    val uid: String,
    /**
     * 상품명
     */
    val name: String,
    /**
     * 결제 상품 유입경로
     */
    val payReferrer: NaverpayProductItemPayReferrer?,
    /**
     * 시작일(YYYYMMDD)
     */
    val startDate: String?,
    /**
     * 종료일(YYYYMMDD)
     */
    val endDate: String?,
    /**
     * 하위 판매자 식별키
     */
    val sellerId: String?,
    /**
     * 결제 상품 개수
     */
    val count: Long
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "categoryType" to categoryType,
        "categoryId" to categoryId,
        "uid" to uid,
        "name" to name,
        "payReferrer" to payReferrer?.let { payReferrer.toJson() },
        "startDate" to startDate?.let { startDate },
        "endDate" to endDate?.let { endDate },
        "sellerId" to sellerId?.let { sellerId },
        "count" to count
    )
}
