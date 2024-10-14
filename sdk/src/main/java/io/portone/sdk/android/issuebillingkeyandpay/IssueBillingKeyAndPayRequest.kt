package io.portone.sdk.android.issuebillingkeyandpay

import android.os.Parcelable
import io.portone.sdk.android.request.type.billingkeyandpay.IssueBillingKeyAndPayMethod
import io.portone.sdk.android.type.Amount
import io.portone.sdk.android.type.Country
import io.portone.sdk.android.type.Customer
import io.portone.sdk.android.type.Locale
import io.portone.sdk.android.type.OfferPeriod
import io.portone.sdk.android.type.PgProvider
import io.portone.sdk.android.type.ProductType
import io.portone.sdk.android.type.WindowType
import io.portone.sdk.android.type.BillingKeyAndPayMethod
import io.portone.sdk.android.request.IssueBillingKeyAndPayRequest as InternalIssueBillingKeyAndPayRequest
import kotlinx.parcelize.Parcelize

@Parcelize
data class IssueBillingKeyAndPayRequest(
    val storeId: String, // 스토어 아이디
    val paymentId: String, // 주문 번호
    val orderName: String, // 주문명
    val amount: Amount,
    val method: BillingKeyAndPayMethod,
    val channelKey: String? = null, // 채널 이름
    val pgProvider: PgProvider? = null, // PG사
    val isTestChannel: Boolean? = null, // 테스트 채널 여부
    val customer: Customer? = null, // 구매자 정보
    val windowType: WindowType? = null, // 결제창 유형
    val noticeUrls: List<String>? = null, // 웹훅 URL
    val locale: Locale? = null, // 결제창 언어
    val isCulturalExpense: Boolean? = null, // 문화비 지출 여부
    val customData: String? = null, // 가맹점 Custom Data
    val offerPeriod: OfferPeriod? = null,
    val appScheme: String? = null, // 앱 URL Scheme
    val productType: ProductType? = null, // 상품 유형(휴대폰 빌링키 발급시 필수 입력)
    val country: Country? = null,
    val bypass: String? = null,
) : Parcelable {
    internal fun toInternal(): InternalIssueBillingKeyAndPayRequest =
        InternalIssueBillingKeyAndPayRequest(
            storeId = storeId,
            paymentId = paymentId,
            orderName = orderName,
            totalAmount = amount.total,
            taxFreeAmount = amount.taxFree,
            vatAmount = amount.vat,
            currency = amount.currency,
            billingKeyAndPayMethod = method.method(),
            channelKey = channelKey,
            pgProvider = pgProvider,
            isTestChannel = isTestChannel,
            customer = customer?.toRequest(),
            customData = customData,
            offerPeriod = offerPeriod?.toRequest(),
            productType = productType,
            country = country,
            bypass = bypass,
            mobile = when (method) {
                is BillingKeyAndPayMethod.Mobile -> method
            }
        )

    private fun BillingKeyAndPayMethod.method(): IssueBillingKeyAndPayMethod =
        when (this) {
            is BillingKeyAndPayMethod.Mobile -> IssueBillingKeyAndPayMethod.MOBILE
        }

}
