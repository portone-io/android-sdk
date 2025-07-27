package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 삼성페이 고객사 유형 (01: 삼성페이 內 쇼핑 / 99: 기타 (기본값))
 */
enum class NiceV2SamPayMallType {
    /**
     * 삼성페이 內 쇼핑
     */
    _01,
    /**
     * 기타 (기본값)
     */
    _99;

    fun toJson(): String = name
}
