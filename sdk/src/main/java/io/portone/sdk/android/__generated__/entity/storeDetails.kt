package io.portone.sdk.__generated__.entity

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.StoreDetailsOpeningHours
import kotlinx.parcelize.Parcelize

/**
 * **상점 정보**
 * 
 * - KSNET 카카오페이의 경우 필수 입력
 * - 나이스페이먼츠의 경우 매출 전표에 표기 할 용도로 선택 입력
 * - KG이니시스 일본결제의 경우 JPPG(gmoPayment) 결제의 상점정보로 사용되거나 편의점 결제 시 영수증 표시 정보로 사용됨.
 */
@Parcelize
data class StoreDetails(
    /**
     * **대표자 이름**
     */
    val ceoFullName: String?,
    /**
     * **전화번호**
     */
    val phoneNumber: String?,
    /**
     * **주소**
     */
    val address: String?,
    /**
     * 우편번호
     */
    val zipcode: String?,
    /**
     * 이메일
     */
    val email: String?,
    /**
     * **사업자명 (상호)**
     */
    val businessName: String?,
    /**
     * **사업자 등록 번호**
     */
    val businessRegistrationNumber: String?,
    /**
     * **상점명**
     */
    val storeName: String?,
    /**
     * 상점명 약어
     */
    val storeNameShort: String?,
    /**
     * 상점명 영문
     */
    val storeNameEn: String?,
    /**
     * 상점명 후리카나 (일본어 읽는법 표기)
     */
    val storeNameKana: String?,
    /**
     * 상점 영업시간 (HH:mm)
     */
    val openingHours: StoreDetailsOpeningHours?,
    /**
     * **상점 연락처 정보 이름**
     * 
     * ex: 문의창구, 연락처, 지원창구
     */
    val contactName: String?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "ceoFullName" to ceoFullName?.let { ceoFullName },
        "phoneNumber" to phoneNumber?.let { phoneNumber },
        "address" to address?.let { address },
        "zipcode" to zipcode?.let { zipcode },
        "email" to email?.let { email },
        "businessName" to businessName?.let { businessName },
        "businessRegistrationNumber" to businessRegistrationNumber?.let { businessRegistrationNumber },
        "storeName" to storeName?.let { storeName },
        "storeNameShort" to storeNameShort?.let { storeNameShort },
        "storeNameEn" to storeNameEn?.let { storeNameEn },
        "storeNameKana" to storeNameKana?.let { storeNameKana },
        "openingHours" to openingHours?.let { openingHours.toJson() },
        "contactName" to contactName?.let { contactName }
    )
}
