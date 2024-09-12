package io.portone.sdk.android.payment

import android.os.Parcelable
import io.portone.sdk.android.type.Amount
import io.portone.sdk.android.request.PaymentRequest as InternalPaymentRequest
import io.portone.sdk.android.type.Address
import io.portone.sdk.android.type.Country
import io.portone.sdk.android.type.Customer
import io.portone.sdk.android.type.Locale
import io.portone.sdk.android.type.OfferPeriod
import io.portone.sdk.android.type.PayMethod
import io.portone.sdk.android.type.PaymentMethod
import io.portone.sdk.android.type.PgProvider
import io.portone.sdk.android.type.Product
import io.portone.sdk.android.type.ProductType
import io.portone.sdk.android.type.StoreDetails
import io.portone.sdk.android.type.WindowType
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentRequest(
    val storeId: String, // 스토어 아이디
    val paymentId: String, // 주문 번호
    val orderName: String, // 주문명
    val amount: Amount, // 결제 금액(실제 결제 금액 X 10^ 해당 currency의 scale factor, 예) $1.50 -> 150)
    val paymentMethod: PaymentMethod,
    val channelKey: String? = null, // 채널 이름
    val pgProvider: PgProvider? = null, // PG사
    val isTestChannel: Boolean? = null, // 테스트 채널 여부
    val channelGroupId: String? = null, // 채널 그룹 아이디
    val customer: Customer? = null, // 구매자 정보
    val windowType: WindowType? = null, // 결제창 유형
    val noticeUrls: List<String>? = null, // 웹훅 URL
    val confirmUrl: String? = null, // Confirm URL
    val appScheme: String? = null, // 앱 URL Scheme
    val isEscrow: Boolean? = null, // 에스크로 결제 여부
    val products: List<Product>? = null, // 에스크로 정보
    val isCulturalExpense: Boolean? = null, // 문화비 지출 여부
    val locale: Locale? = null, // 결제창 언어
    val customData: String? = null, // 가맹점 Custom Data
    val offerPeriod: OfferPeriod? = null,
    val productType: ProductType? = null,
    val storeDetails: StoreDetails? = null,
    val country: Country? = null,
    val shippingAddress: Address? = null, // 배송지 주소
    val promotionId: String? = null, // 프로모션 아이디
    val bypass: String? = null, // TODO 작업 필요
) : Parcelable {
    internal fun toInternal(): InternalPaymentRequest =
        InternalPaymentRequest(
            storeId = storeId,
            paymentId = paymentId,
            orderName = orderName,
            totalAmount = amount.total,
            payMethod = paymentMethod.payMethod(),
            channelKey = channelKey,
            pgProvider = pgProvider,
            isTestChannel = isTestChannel,
            channelGroupId = channelGroupId,
            taxFreeAmount = amount.taxFree,
            vatAmount = amount.vat,
            currency = amount.currency,
            customer = customer?.toRequest(),
            windowType = windowType,
            noticeUrls = noticeUrls,
            confirmUrl = confirmUrl,
            appScheme = appScheme,
            isEscrow = isEscrow,
            isCulturalExpense = isCulturalExpense,
            locale = locale,
            customData = customData,
            products = products,
            productType = productType,
            storeDetails = storeDetails,
            offerPeriod = offerPeriod?.toRequest(),
            country = country,
            shippingAddress = shippingAddress?.toRequest(),
            promotionId = promotionId,
            bypass = bypass,
            card = when (paymentMethod) {
                is PaymentMethod.Card -> paymentMethod
                is PaymentMethod.EasyPay -> null
                is PaymentMethod.GiftCertificate -> null
                is PaymentMethod.Mobile -> null
                is PaymentMethod.Transfer -> null
                is PaymentMethod.VirtualAccount -> null
            },
            virtualAccount = when (paymentMethod) {
                is PaymentMethod.Card -> null
                is PaymentMethod.EasyPay -> null
                is PaymentMethod.GiftCertificate -> null
                is PaymentMethod.Mobile -> null
                is PaymentMethod.Transfer -> null
                is PaymentMethod.VirtualAccount -> paymentMethod.toRequest()
            },
            transfer = when (paymentMethod) {
                is PaymentMethod.Card -> null
                is PaymentMethod.EasyPay -> null
                is PaymentMethod.GiftCertificate -> null
                is PaymentMethod.Mobile -> null
                is PaymentMethod.Transfer -> paymentMethod
                is PaymentMethod.VirtualAccount -> null
            },
            mobile = when (paymentMethod) {
                is PaymentMethod.Card -> null
                is PaymentMethod.EasyPay -> null
                is PaymentMethod.GiftCertificate -> null
                is PaymentMethod.Mobile -> paymentMethod
                is PaymentMethod.Transfer -> null
                is PaymentMethod.VirtualAccount -> null
            },
            giftCertificate = when (paymentMethod) {
                is PaymentMethod.Card -> null
                is PaymentMethod.EasyPay -> null
                is PaymentMethod.GiftCertificate -> paymentMethod
                is PaymentMethod.Mobile -> null
                is PaymentMethod.Transfer -> null
                is PaymentMethod.VirtualAccount -> null
            },
            easyPay = when (paymentMethod) {
                is PaymentMethod.Card -> null
                is PaymentMethod.EasyPay -> paymentMethod
                is PaymentMethod.GiftCertificate -> null
                is PaymentMethod.Mobile -> null
                is PaymentMethod.Transfer -> null
                is PaymentMethod.VirtualAccount -> null
            },
        )

    private fun PaymentMethod.payMethod(): PayMethod =
        when (this) {
            is PaymentMethod.Card -> PayMethod.CARD
            is PaymentMethod.EasyPay -> PayMethod.EASY_PAY
            is PaymentMethod.GiftCertificate -> PayMethod.GIFT_CERTIFICATE
            is PaymentMethod.Mobile -> PayMethod.MOBILE
            is PaymentMethod.Transfer -> PayMethod.TRANSFER
            is PaymentMethod.VirtualAccount -> PayMethod.VIRTUAL_ACCOUNT
        }
}
