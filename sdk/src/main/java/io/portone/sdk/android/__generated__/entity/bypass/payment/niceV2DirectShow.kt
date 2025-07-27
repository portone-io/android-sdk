package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 다이렉트 호출 결제 수단 (BANK: 계좌이체/CELLPHONE: 휴대폰 소액결제)
 */
enum class NiceV2DirectShow {
    /**
     * 계좌이체
     */
    BANK,
    /**
     * 휴대폰 소액결제
     */
    CELLPHONE;

    fun toJson(): String = name
}
