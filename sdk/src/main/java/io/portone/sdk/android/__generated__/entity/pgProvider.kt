package io.portone.sdk.__generated__.entity

/**
 * **PG사 구분코드**
 */
enum class PgProvider {
    HTML5_INICIS,
    PAYPAL,
    INICIS,
    DANAL,
    NICE,
    DANAL_TPAY,
    UPLUS,
    NAVERPAY,
    SETTLE,
    KCP,
    MOBILIANS,
    KAKAOPAY,
    NAVERCO,
    KICC,
    EXIMBAY,
    SMILEPAY,
    PAYCO,
    KCP_BILLING,
    ALIPAY,
    CHAI,
    BLUEWALNUT,
    SMARTRO,
    PAYMENTWALL,
    TOSSPAYMENTS,
    KCP_QUICK,
    DAOU,
    GALAXIA,
    TOSSPAY,
    KCP_DIRECT,
    SETTLE_ACC,
    SETTLE_FIRM,
    INICIS_UNIFIED,
    KSNET,
    PAYPAL_V2,
    SMARTRO_V2,
    NICE_V2,
    TOSS_BRANDPAY,
    WELCOME,
    TOSSPAY_V2,
    INICIS_V2,
    KPN,
    KCP_V2,
    HYPHEN,
    EXIMBAY_V2;

    fun toJson(): String = name
}
