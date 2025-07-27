package io.portone.sdk.__generated__.entity.bypass.payment

enum class KpnCardSelect {
    /**
     * 해외카드
     */
    GLOBAL,
    /**
     * 11Pay
     */
    _11PAY,
    /**
     * 구인증
     */
    LEGACY_AUTH,
    /**
     * 키인
     */
    KEY_IN;

    fun toJson(): String = name
}
