package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 가상계좌, 계좌이체 시 현금영수증 노출 여부
 */
enum class KcpV2DispTax {
    Y,
    N,
    R,
    E;

    fun toJson(): String = name
}
