package io.portone.sdk.android.request

import io.portone.sdk.android.PortOne
import io.portone.sdk.android.request.type.Customer
import io.portone.sdk.android.type.PgProvider
import io.portone.sdk.android.type.WindowType
import kotlinx.serialization.Serializable

@Serializable
internal data class IdentityVerificationRequest(
    val storeId: String, // 스토어 아이디
    val identityVerificationId: String, // 고객사에서 채번하는 본인인증 건에 대한 고유 ID
    val channelKey: String? = null, // 채널 이름
    val pgProvider: PgProvider? = null, // PG사
    val isTestChannel: Boolean? = null, // 테스트 채널 여부
    val customer: Customer? = null,
    val windowType: WindowType? = null, // 결제창 유형
    private val redirectUrl: String? = PortOne.REDIRECT_URL, //  인증 프로세스 종료 후 리디렉션 될 URL
    val customData: String? = null, // 고객사 Custom Data
    val bypass: String? = null,
)
