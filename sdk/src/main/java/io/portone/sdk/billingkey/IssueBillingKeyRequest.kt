package io.portone.sdk.billingkey

import android.os.Parcelable
import io.portone.sdk.PortOne
import io.portone.sdk.entity.BillingKeyMethod
import io.portone.sdk.entity.Currency
import io.portone.sdk.entity.Customer
import io.portone.sdk.entity.Locale
import io.portone.sdk.entity.OfferPeriod
import io.portone.sdk.entity.PgProvider
import io.portone.sdk.entity.ProductType
import io.portone.sdk.entity.WindowType
import io.portone.sdk.entity.paymentmethod.Card
import io.portone.sdk.entity.paymentmethod.Mobile
import io.portone.sdk.identityverification.IdentityVerificationBypass
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class IssueBillingKeyRequest(
    val storeId: String, // 스토어 아이디
    val issueId: String? = null, // 주문 번호
    val issueName: String? = null, // 주문명
    val displayAmount: Int? = null, //
    val billingKeyMethod: BillingKeyMethod,
    val channelKey: String? = null, // 채널 이름
    val pgProvider: PgProvider? = null, // PG사
    val isTestChannel: Boolean? = null, // 테스트 채널 여부
    val customer: Customer? = null, // 구매자 정보
    val windowType: WindowType? = null, // 결제창 유형
    private val redirectUrl: String? = PortOne.REDIRECT_URL, //  결제 프로세스 종료 후 리디렉션 될 URL
    val noticeUrls: List<String>? = null, // 웹훅 URL
    val confirmUrl: String? = null, // Confirm URL
    val appScheme: String? = null, // 앱 URL Scheme
    val currency: Currency? = null, // 통화
    val locale: Locale? = null, // 결제창 언어
    val customData: String? = null, // 가맹점 Custom Data
    val offerPeriod: OfferPeriod? = null,
    val productType: ProductType? = null,
    val bypass: String? = null, // TODO 작업 필요
    val card: Card? = null,
    val mobile: Mobile? = null,
) : Parcelable
