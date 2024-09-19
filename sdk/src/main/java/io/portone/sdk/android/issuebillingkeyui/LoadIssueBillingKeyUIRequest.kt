package io.portone.sdk.android.issuebillingkeyui

import android.os.Parcelable
import io.portone.sdk.android.IssueBillingKeyUIType
import io.portone.sdk.android.request.type.billingkey.IssueBillingKeyMethod
import io.portone.sdk.android.request.LoadIssueBillingKeyUIRequest as InternalLoadIssueBillingKeyUIRequest
import io.portone.sdk.android.type.BillingKeyMethod
import io.portone.sdk.android.type.Currency
import io.portone.sdk.android.type.Customer
import io.portone.sdk.android.type.Locale
import io.portone.sdk.android.type.PgProvider
import io.portone.sdk.android.type.ProductType
import io.portone.sdk.android.type.WindowType
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoadIssueBillingKeyUIRequest(
    val uiType: IssueBillingKeyUIType,
    val storeId: String, // 스토어 아이디
    val issueId: String? = null, // 주문 번호
    val issueName: String? = null, // 주문명
    val displayAmount: Int? = null,
    val currency: Currency? = null, // 통화
    val billingKeyMethod: BillingKeyMethod,
    val channelKey: String? = null, // 채널 이름
    val pgProvider: PgProvider? = null, // PG사
    val isTestChannel: Boolean? = null, // 테스트 채널 여부
    val customer: Customer? = null, // 구매자 정보
    val windowType: WindowType? = null, // 결제창 유형
    val noticeUrls: List<String>? = null, // 웹훅 URL
    val confirmUrl: String? = null, // Confirm URL
    val appScheme: String? = null, // 앱 URL Scheme
    val locale: Locale? = null, // 결제창 언어
    val customData: String? = null, // 가맹점 Custom Data
    val productType: ProductType? = null,
    val bypass: String? = null, // TODO 작업 필요
) : Parcelable {
    internal fun toInternal(): InternalLoadIssueBillingKeyUIRequest =
        InternalLoadIssueBillingKeyUIRequest(
            uiType = uiType,
            storeId = storeId,
            issueId = issueId,
            issueName = issueName,
            displayAmount = displayAmount,
            currency = currency,
            billingKeyMethod = billingKeyMethod.billingKeyMethod(),
            channelKey = channelKey,
            pgProvider = pgProvider,
            isTestChannel = isTestChannel,
            customer = customer?.toRequest(),
            windowType = windowType,
            confirmUrl = confirmUrl,
            appScheme = appScheme,
            locale = locale,
            customData = customData,
            productType = productType,
            bypass = bypass,
        )

    private fun BillingKeyMethod.billingKeyMethod(): IssueBillingKeyMethod =
        when (this) {
            is BillingKeyMethod.Card -> IssueBillingKeyMethod.CARD
            is BillingKeyMethod.EasyPay -> IssueBillingKeyMethod.EASY_PAY
            is BillingKeyMethod.Mobile -> IssueBillingKeyMethod.MOBILE
            is BillingKeyMethod.Paypal -> IssueBillingKeyMethod.PAYPAL
        }
}
