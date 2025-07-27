package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 현금영수증 발급 여부
 */
enum class EximbayV2TaxReceiptStatus {
    Y,
    N;

    fun toJson(): String = name
}
