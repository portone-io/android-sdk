package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 하부 가맹점 정보. PG 업종 가맹점인 경우에만 필수 값
 */
@Parcelize
data class NaverpaySubMerchantInfo(
    /**
     * 하부 가맹점 명
     */
    val subMerchantName: String,
    /**
     * 하부 가맹점 ID
     */
    val subMerchantId: String,
    /**
     * 하부 가맹점 사업자 번호(숫자 10자리)
     */
    val subMerchantBusinessNo: String,
    /**
     * 하부 가맹점 결제 키
     */
    val subMerchantPayId: String,
    /**
     * 하부 가맹점 대표 전화번호
     */
    val subMerchantTelephoneNo: String,
    /**
     * 하부 가맹점 고객 서비스 URL
     */
    val subMerchantCustomerServiceUrl: String
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "subMerchantName" to subMerchantName,
        "subMerchantId" to subMerchantId,
        "subMerchantBusinessNo" to subMerchantBusinessNo,
        "subMerchantPayId" to subMerchantPayId,
        "subMerchantTelephoneNo" to subMerchantTelephoneNo,
        "subMerchantCustomerServiceUrl" to subMerchantCustomerServiceUrl
    )
}
