package io.portone.sdk.__generated__.entity

enum class WindowType {
    IFRAME,
    POPUP,
    REDIRECTION,
    UI;

    fun toJson(): String = name
}
