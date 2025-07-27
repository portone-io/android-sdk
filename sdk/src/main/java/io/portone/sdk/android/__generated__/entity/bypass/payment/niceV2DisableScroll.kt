package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 결제창 스크롤 미사용 여부 (PC Only, Y: 미사용 / N(default): 사용)
 */
enum class NiceV2DisableScroll {
    Y,
    N;

    fun toJson(): String = name
}
