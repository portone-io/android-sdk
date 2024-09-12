package io.portone.sdk.android.identityverification

import android.os.Parcelable
import io.portone.sdk.android.request.IdentityVerificationRequest as InternalIdentityVerificationRequest
import io.portone.sdk.android.type.Customer
import io.portone.sdk.android.type.PgProvider
import io.portone.sdk.android.type.WindowType
import kotlinx.parcelize.Parcelize

@Parcelize
data class IdentityVerificationRequest(
    val storeId: String, // 스토어 아이디
    val identityVerificationId: String, // 고객사에서 채번하는 본인인증 건에 대한 고유 ID
    val channelKey: String? = null, // 채널 이름
    val pgProvider: PgProvider? = null, // PG사
    val isTestChannel: Boolean? = null, // 테스트 채널 여부
    val customer: Customer? = null,
    val windowType: WindowType? = null, // 결제창 유형
    val customData: String? = null, // 고객사 Custom Data
    val bypass: IdentityVerificationBypass? = null,
) : Parcelable {
    internal fun toInternal(): InternalIdentityVerificationRequest =
        InternalIdentityVerificationRequest(
            storeId = storeId,
            identityVerificationId = identityVerificationId,
            channelKey = channelKey,
            pgProvider = pgProvider,
            customer = customer?.toRequest(),
            windowType = windowType,
            customData = customData,
            bypass = bypass,
        )
}
