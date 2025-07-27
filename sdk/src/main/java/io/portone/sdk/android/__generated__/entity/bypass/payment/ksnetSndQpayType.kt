package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 간편 결제 표시 구분
 */
enum class KsnetSndQpayType {
    /**
     * 간편 결제 표시
     */
    Y,
    /**
     * 간편 결제 미표시
     */
    N;

    fun toJson(): String = name
}
