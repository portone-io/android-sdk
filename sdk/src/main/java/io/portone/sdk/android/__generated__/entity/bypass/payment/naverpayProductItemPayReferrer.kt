package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 결제 상품 유입경로
 */
enum class NaverpayProductItemPayReferrer {
    NAVER_BOOK,
    NAVER_MUSIC,
    NAVER_SHOPPING,
    NAVER_MAP,
    NAVER_PLACE,
    SEARCH_AD,
    NAVER_SEARCH,
    BRAND_SEARCH,
    PARTNER_DIRECT,
    ETC;

    fun toJson(): String = name
}
