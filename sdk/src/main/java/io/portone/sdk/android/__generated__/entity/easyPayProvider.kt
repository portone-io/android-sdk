package io.portone.sdk.__generated__.entity

/**
 * PG 제휴로 간편결제를 이용할 때, 간편결제 UI를 직접 호출할 수 있는 간편결제
 */
enum class EasyPayProvider {
    /**
     * 페이코
     * 
     * - 토스페이먼츠
     * - 나이스페이먼츠
     * - KG이니시스
     * - 스마트로
     * - KSNET
     * - 한국결제네트웍스
     * - 웰컴페이먼츠
     */
    PAYCO,
    /**
     * 삼성페이
     * 
     * - 토스페이먼츠
     * - 나이스페이먼츠
     * - KG이니시스
     * - NHN KCP
     * - 스마트로
     * - 한국결제네트웍스
     */
    SAMSUNGPAY,
    /**
     * SSG페이
     * 
     * - 토스페이먼츠
     * - 나이스페이먼츠
     * - KG이니시스
     * - NHN KCP
     * - KSNET
     */
    SSGPAY,
    /**
     * 카카오페이
     * 
     * - 토스페이먼츠
     * - 나이스페이먼츠
     * - KG이니시스
     * - 스마트로
     * - NHN KCP
     * - KSNET
     * - 한국결제네트웍스
     * - 웰컴페이먼츠
     */
    KAKAOPAY,
    /**
     * 네이버페이
     * 
     * - 토스페이먼츠
     * - 나이스페이먼츠
     * - KG이니시스
     * - 스마트로
     * - NHN KCP
     * - KSNET
     * - 한국결제네트웍스
     */
    NAVERPAY,
    /**
     * 차이페이
     */
    CHAI,
    /**
     * L페이
     * 
     * - 토스페이먼츠
     * - 나이스페이먼츠
     * - KG이니시스
     * - 스마트로
     * - KSNET
     * - 웰컴페이먼츠
     */
    LPAY,
    /**
     * K페이
     */
    KPAY,
    /**
     * 토스페이
     * 
     * - 토스페이먼츠
     * - KG이니시스
     * - NHN KCP
     * - 스마트로
     * - 한국결제네트웍스
     * - 웰컴페이먼츠
     */
    TOSSPAY,
    /**
     * LG페이
     * 
     * - 토스페이먼츠
     */
    LGPAY,
    /**
     * 애플페이
     * 
     * - 토스페이먼츠
     * - 나이스페이먼츠
     * - KG이니시스
     * - NHN KCP
     */
    APPLEPAY,
    /**
     * 핀페이
     * 
     * - 스마트로
     */
    PINPAY,
    /**
     * SK페이
     * 
     * - 나이스페이먼츠
     */
    SKPAY,
    /**
     * 토스 브랜드페이
     */
    TOSS_BRANDPAY,
    /**
     * 하이픈
     */
    HYPHEN,
    /**
     * 라인페이
     * 
     * - 스마트로
     */
    LINEPAY,
    /**
     * 티머니
     * 
     * - 스마트로
     */
    TMONEY,
    /**
     * PayPay
     * 
     * - KG이니시스 JPPG/SBPS 일본결제
     */
    PAYPAY,
    /**
     * 아마존페이
     * 
     * - KG이니시스 JPPG 일본결제
     */
    AMAZONPAY,
    /**
     * 라쿠텐페이
     * 
     * - KG이니시스 JPPG 일본결제
     */
    RAKUTENPAY,
    /**
     * dBarai
     * 
     * - KG이니시스 JPPG 일본결제
     */
    DBARAI,
    /**
     * auPAY
     * 
     * - KG이니시스 JPPG 일본결제
     */
    AUPAY,
    /**
     * Merpay
     * 
     * - KG이니시스 JPPG 일본결제
     */
    MERPAY;

    fun toJson(): String = name
}
