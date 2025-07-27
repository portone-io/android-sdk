package io.portone.sdk.__generated__.entity.bypass.identityVerification

/**
 * **다날 bypass 파라미터**
 */
data class DanalIdentityVerificationBypass(
    /**
     * **고객사 서비스 URL 혹은 본인확인 기능 사용 경로**
     * 
     * - 본인확인 기능을 앱에서만 사용하는 경우 서비스 홈 URL 입력. Ex) `m.login.MarketC.co.kr`
     * - 본인확인 기능을 사용하는 웹 페이지가 존재할 경우 해당 URL 입력. Ex) `www.MarketA.co.kr`
     * - 웹 서비스 URL 자체가 존재하지 않는 경우 서비스 이름 (app 이름) 입력. Ex) `마켓A`
     * - 해당 값을 넘기지 않을 경우 `포트원`으로 default 값을 채웁니다.
     */
    val cptitle: String?,
    /**
     * **본인인증을 진행할 수 있는 최소 만 나이**
     * 
     * 해당 값을 채워서 요청할 경우 본인인증을 진행할 수 있는 최소 만 나이를 설정할 수 있습니다.
     */
    val agelimit: Long?,
    /**
     * **통신사 정보**
     * 
     * 인증 화면에서 해당 통신사만 활성화시킬 수 있습니다.
     * 
     * 가능한 값: `SKT`, `KTF`, `LGT`, `MVNO`
     * 
     * 여러 개의 통신사를 활성화시키려면 위 값들을 semicolon(`;`) 으로 이어야 합니다. ex) `SKT;KTF`
     */
    val isCarrier: String?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "CPTITLE" to cptitle?.let { cptitle },
        "AGELIMIT" to agelimit?.let { agelimit },
        "IsCarrier" to isCarrier?.let { isCarrier }
    )
}
