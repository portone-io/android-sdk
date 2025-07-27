package io.portone.sdk.__generated__.entity.bypass.payment

import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2SettingsCallFromApp

/**
 * 설정 정보
 */
data class EximbayV2Settings(
    /**
     * 인앱 웹뷰 여부
     */
    val callFromApp: EximbayV2SettingsCallFromApp?,
    /**
     * 해외 결제 가맹점에서 국내 결제를 사용할 경우 `KR`
     */
    val issuerCountry: String?,
    /**
     * 입금 만료 일자 (yyyyMMddHH)
     */
    val virtualaccountExpiryDate: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "call_from_app" to callFromApp?.let { callFromApp.toJson() },
        "issuer_country" to issuerCountry?.let { issuerCountry },
        "virtualaccount_expiry_date" to virtualaccountExpiryDate?.let { virtualaccountExpiryDate }
    )
}
