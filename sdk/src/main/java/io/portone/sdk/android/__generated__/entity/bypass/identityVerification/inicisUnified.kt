package io.portone.sdk.__generated__.entity.bypass.identityVerification

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.identityVerification.InicisUnifiedDirectAgency
import io.portone.sdk.__generated__.entity.bypass.identityVerification.InicisUnifiedFlgFixedUser
import kotlinx.parcelize.Parcelize

/**
 * **KG이니시스 bypass 파라미터**
 */
@Parcelize
data class InicisUnifiedIdentityVerificationBypass(
    /**
     * **단독 노출할 인증 업체 코드**
     * 
     * 인증 업체 선택 화면 없이 설정한 인증 업체를 통해 인증하도록 합니다.
     */
    val directAgency: InicisUnifiedDirectAgency?,
    /**
     * **인증 창에서 고객 정보를 미리 채울지 여부**
     * 
     * `Y`, `N` 중 하나를 입력해주세요.
     * 
     * `Y`인 경우 이름, 연락처, 출생년도, 출생월, 출생일을 필수로 입력해야 합니다.
     */
    val flgFixedUser: InicisUnifiedFlgFixedUser,
    /**
     * **인증 창에 표시할 로고 URL**
     * 
     * 인증 창 좌측 상단 KG이니시스 로고 대신 들어갈 로고의 URL입니다.
     * 최적 크기는 가로 164px, 세로 28px입니다.
     * 
     * HTTPS URL을 입력합니다. (HTTP URL인 경우 표시되지 않을 수 있습니다.)
     */
    val logoUrl: String?,
    /**
     * DI를 생성할 때 사용할 salt
     */
    val diCode: String?,
    /**
     * **성별 및 외국인 정보 별도 입력 여부**
     * 
     * `Y`(기본값)인 경우 성별 및 외국인 정보를 제공하지 않는 일부 인증기관 사용 시 성별 및 외국인 정보 입력란을 표시합니다. **해당 정보는 인증 기관을 통해 검증되지 않습니다.**
     * 
     * `N`인 경우 네이버, 카카오에서 사용자가 성별 및 외국인 정보를 입력하는 칸을 표시하지 않습니다.
     */
    val frgndInfo: String?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "directAgency" to directAgency?.let { directAgency.toJson() },
        "flgFixedUser" to flgFixedUser.toJson(),
        "logoUrl" to logoUrl?.let { logoUrl },
        "DI_CODE" to diCode?.let { diCode },
        "FRGNDInfo" to frgndInfo?.let { frgndInfo }
    )
}
