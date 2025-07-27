package io.portone.sdk.__generated__.entity

/**
 * **결제창이 iframe 방식일 경우 결제창에 적용할 속성**
 */
data class Iframe(
    /**
     * `false`로 설정하면 결제창 배경이 투명해집니다.
     */
    val dim: Boolean?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "dim" to dim?.let { dim }
    )
}
