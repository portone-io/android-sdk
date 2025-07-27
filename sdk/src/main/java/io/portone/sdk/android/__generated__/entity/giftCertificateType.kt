package io.portone.sdk.__generated__.entity

/**
 * **상품권 종류**
 */
enum class GiftCertificateType {
    /**
     * 도서문화상품권
     * | KG이니시스
     */
    BOOKNLIFE,
    /**
     * 스마트문상, (구)게임문화상품권
     */
    SMART_MUNSANG,
    /**
     * 컬쳐랜드 문화상품권
     */
    CULTURELAND,
    /**
     * 문화상품권
     */
    CULTURE_GIFT;

    fun toJson(): String = name
}
