package io.portone.sdk.__generated__.entity.bypass.issueBillingKey

/**
 * 개인/법인카드 선택 옵션
 */
enum class InicisV2CardUse {
    /**
     * 개인카드만 선택 가능
     */
    percard,
    /**
     * 법인 카드만 선택 가능
     */
    cocard;

    fun toJson(): String = name
}
