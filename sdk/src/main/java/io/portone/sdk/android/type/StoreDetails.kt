package io.portone.sdk.android.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class StoreDetails(
    val ceoFullName: String? = null, // 상점 대표자 이름
    val phoneNumber: String? = null, // 상점 연락처
    val address: String? = null, // 상점 주소
    val zipcode: String? = null, // 상점 우편번호
    val businessName: String? = null, // 상점 사업자 명
    val businessRegistrationNumber: String? = null // 상점 사업자 등록 번호
): Parcelable
