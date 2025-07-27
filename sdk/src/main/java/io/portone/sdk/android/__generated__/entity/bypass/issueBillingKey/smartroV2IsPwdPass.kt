package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

/**
 * 결제 비밀번호 등록 Skip 여부
 */
enum class SmartroV2IsPwdPass {
    /**
     * 비밀번호 설정 미사용
     */
    Y,
    /**
     * 비밀번호 설정 사용
     */
    N;

    fun toJson(): String = name
}
