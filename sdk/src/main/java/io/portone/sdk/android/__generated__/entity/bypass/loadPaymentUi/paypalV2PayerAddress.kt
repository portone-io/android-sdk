package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

import io.portone.sdk.__generated__.entity.Country

data class PaypalV2PayerAddress(
    /**
     * 구매자 주소지 정보
     */
    val addressLine1: String?,
    val addressLine2: String?,
    val adminArea1: String?,
    val adminArea2: String?,
    /**
     * 우편번호
     */
    val postalCode: String?,
    /**
     * **국가**
     * 
     * [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) 코드입니다.
     */
    val countryCode: Country
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "address_line_1" to addressLine1?.let { addressLine1 },
        "address_line_2" to addressLine2?.let { addressLine2 },
        "admin_area_1" to adminArea1?.let { adminArea1 },
        "admin_area_2" to adminArea2?.let { adminArea2 },
        "postal_code" to postalCode?.let { postalCode },
        "country_code" to countryCode.toJson()
    )
}
