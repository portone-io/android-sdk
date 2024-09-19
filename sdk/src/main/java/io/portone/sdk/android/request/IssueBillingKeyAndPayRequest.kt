package io.portone.sdk.android.request

import io.portone.sdk.android.PortOne
import io.portone.sdk.android.request.type.billingkeyandpay.IssueBillingKeyAndPayMethod
import io.portone.sdk.android.type.Country
import io.portone.sdk.android.type.Currency
import io.portone.sdk.android.request.type.Customer
import io.portone.sdk.android.type.Locale
import io.portone.sdk.android.request.type.OfferPeriod
import io.portone.sdk.android.type.BillingKeyAndPayMethod
import io.portone.sdk.android.type.PgProvider
import io.portone.sdk.android.type.ProductType
import io.portone.sdk.android.type.WindowType
import kotlinx.serialization.Serializable

@Serializable
internal data class IssueBillingKeyAndPayRequest(
    val storeId: String, // 스토어 아이디
    val paymentId: String, // 주문 번호
    val orderName: String, // 주문명
    val totalAmount: Long, // 결제 금액(실제 결제 금액 X 10^ 해당 currency의 scale factor, 예) $1.50 -> 150)
    val currency: Currency, // 통화
    val billingKeyAndPayMethod: IssueBillingKeyAndPayMethod,
    val channelKey: String? = null, // 채널 이름
    val pgProvider: PgProvider? = null, // PG사
    val isTestChannel: Boolean? = null, // 테스트 채널 여부
    val taxFreeAmount: Long? = null, // 면세 금액
    val vatAmount: Long? = null, // 부가세
    val customer: Customer? = null, // 구매자 정보
    val windowType: WindowType? = null, // 결제창 유형
    private val redirectUrl: String? = PortOne.REDIRECT_URL, //  결제 프로세스 종료 후 리디렉션 될 URL
    val noticeUrls: List<String>? = null, // 웹훅 URL
    val locale: Locale? = null, // 결제창 언어
    val isCulturalExpense: Boolean? = null, // 문화비 지출 여부
    val customData: String? = null, // 가맹점 Custom Data
    val offerPeriod: OfferPeriod? = null,
    val appScheme: String? = null, // 앱 URL Scheme
    val productType: ProductType? = null, // 상품 유형(휴대폰 빌링키 발급시 필수 입력)
    val country: Country? = null,
    val bypass: String? = null,
    val mobile: BillingKeyAndPayMethod.Mobile? = null,
)
