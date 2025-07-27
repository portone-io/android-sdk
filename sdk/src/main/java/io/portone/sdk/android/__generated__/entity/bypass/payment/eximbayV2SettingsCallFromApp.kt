package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 인앱 웹뷰 여부
 */
enum class EximbayV2SettingsCallFromApp {
    Y,
    N;

    fun toJson(): String = name
}
