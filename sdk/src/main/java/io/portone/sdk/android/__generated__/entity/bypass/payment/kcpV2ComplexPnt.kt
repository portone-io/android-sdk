package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 포인트 결제의 경우 신용카드 + 포인트 결제인데, N으로 설정 시 포인트로만 결제가 이루어짐
 */
enum class KcpV2ComplexPnt {
    Y,
    N;

    fun toJson(): String = name
}
