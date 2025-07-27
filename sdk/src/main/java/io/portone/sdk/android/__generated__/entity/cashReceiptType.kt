package io.portone.sdk.__generated__.entity

/**
 * **현금영수증 발급 유형**
 */
enum class CashReceiptType {
    /**
     * 소득공제(개인)
     */
    PERSONAL,
    /**
     * 지출증빙(사업자)
     */
    CORPORATE,
    /**
     * 미발행(PG 설정에 따라 무기명으로 자진 발급될 수 있음)
     */
    ANONYMOUS;

    fun toJson(): String = name
}
