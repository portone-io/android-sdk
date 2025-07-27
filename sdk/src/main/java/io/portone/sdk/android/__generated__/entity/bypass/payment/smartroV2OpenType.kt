package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 해외 카드만 결제를 허용할지 여부(기본값: `"KR"`)
 * 
 * `"KR"`, `"EN"` 중 하나의 값으로 입력해주세요.
 */
enum class SmartroV2OpenType {
    KR,
    EN;

    fun toJson(): String = name
}
