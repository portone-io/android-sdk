package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 결제창 스킨 색상 설정
 * 
 * `"red", "green", "purple", "gray", "dark"` 중 하나의 값으로 입력해주세요.
 */
enum class NiceV2SkinType {
    red,
    green,
    purple,
    gray,
    dark;

    fun toJson(): String = name
}
