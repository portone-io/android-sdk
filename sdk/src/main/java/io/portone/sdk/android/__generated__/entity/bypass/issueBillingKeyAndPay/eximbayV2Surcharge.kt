package io.portone.sdk.__generated__.entity.bypass.issueBillingKeyAndPay

data class EximbayV2Surcharge(
    /**
     * **항목명**
     */
    val name: String,
    /**
     * **수량**
     */
    val quantity: String,
    /**
     * **단가 (음수 가능)**
     */
    val unitPrice: String
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "name" to name,
        "quantity" to quantity,
        "unit_price" to unitPrice
    )
}
