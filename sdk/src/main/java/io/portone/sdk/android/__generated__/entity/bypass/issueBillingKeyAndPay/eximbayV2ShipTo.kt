package io.portone.sdk.__generated__.entity.bypass.issueBillingKeyAndPay

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * **배송지 정보**
 */
@Parcelize
data class EximbayV2ShipTo(
    /**
     * **배송지 도시**
     */
    val city: String,
    /**
     * **배송지 국가 (ISO 3166 두 자리 국가 코드)**
     */
    val country: String,
    /**
     * **수신인의 성을 제외한 이름**
     */
    val firstName: String,
    /**
     * **수신인의 성**
     */
    val lastName: String,
    /**
     * **수신인 전화번호**
     */
    val phoneNumber: String,
    /**
     * **배송지 우편번호**
     */
    val postalCode: String,
    /**
     * **배송지가 미국 혹은 캐나다인 경우, 배송지 주 정보**
     */
    val state: String,
    /**
     * **배송지 상세 주소**
     */
    val street1: String
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "city" to city,
        "country" to country,
        "first_name" to firstName,
        "last_name" to lastName,
        "phone_number" to phoneNumber,
        "postal_code" to postalCode,
        "state" to state,
        "street1" to street1
    )
}
