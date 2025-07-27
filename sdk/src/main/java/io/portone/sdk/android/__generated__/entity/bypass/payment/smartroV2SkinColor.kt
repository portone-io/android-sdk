package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * UI 스타일 (기본값: `"RED"`)
 * 
 * `"RED"`, `"GREEN"`, `"BLUE"`, `"PURPLE"` 중 하나의 값으로 입력해주세요.
 */
enum class SmartroV2SkinColor {
    RED,
    GREEN,
    BLUE,
    PURPLE;

    fun toJson(): String = name
}
