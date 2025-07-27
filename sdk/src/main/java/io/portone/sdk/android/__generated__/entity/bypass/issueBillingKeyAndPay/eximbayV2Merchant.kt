package io.portone.sdk.__generated__.entity.bypass.issueBillingKeyAndPay

data class EximbayV2Merchant(
    /**
     * **상점명**
     */
    val shop: String
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "shop" to shop
    )
}
