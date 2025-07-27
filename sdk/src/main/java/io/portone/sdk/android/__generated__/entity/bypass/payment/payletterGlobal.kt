package io.portone.sdk.__generated__.entity.bypass.payment

/**
 * 페이레터 해외결제 bypass 파라미터
 */
data class PayletterGlobalBypass(
    /**
     * **결제수단 지정용 파라미터**
     * 
     * - 해외카드 인증 : `PLCreditCard`
     * - 해외카드 비인증(3DS) : `PLCreditCardMpi`
     * - 유니온페이 : `PLUnionPay_HC`
     * - 위챗페이 PC결제: `WeChatPayQRCodePayment`
     * - 위챗페이 모바일결제 : `WeChatPayH5Payment`
     * - 알리페이 : `ICBAlipay`
     */
    val pginfo: String?,
    /**
     * 고객사 서비스명, WeChatPay, Alipay 이용 시 필수 입력
     */
    val servicename: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "pginfo" to pginfo?.let { pginfo },
        "servicename" to servicename?.let { servicename }
    )
}
