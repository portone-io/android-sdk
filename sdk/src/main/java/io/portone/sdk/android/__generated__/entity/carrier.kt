package io.portone.sdk.__generated__.entity

/**
 * 통신사 코드
 */
enum class Carrier {
    /**
     * SK텔레콤
     */
    SKT,
    /**
     * KT
     */
    KT,
    /**
     * LG U+
     */
    LGU,
    /**
     * 헬로모바일
     */
    HELLO,
    /**
     * 티플러스
     */
    KCT,
    /**
     * SK 7mobile
     */
    SK7;

    fun toJson(): String = name
}
