package io.portone.sdk.payment

import android.os.Parcelable
import io.portone.sdk.PortOne
import io.portone.sdk.entity.Address
import io.portone.sdk.entity.Country
import io.portone.sdk.entity.Currency
import io.portone.sdk.entity.Customer
import io.portone.sdk.entity.Locale
import io.portone.sdk.entity.OfferPeriod
import io.portone.sdk.entity.PayMethod
import io.portone.sdk.entity.PgProvider
import io.portone.sdk.entity.Product
import io.portone.sdk.entity.ProductType
import io.portone.sdk.entity.StoreDetails
import io.portone.sdk.entity.WindowType
import io.portone.sdk.entity.paymentmethod.Card
import io.portone.sdk.entity.paymentmethod.EasyPay
import io.portone.sdk.entity.paymentmethod.GiftCertificate
import io.portone.sdk.entity.paymentmethod.Mobile
import io.portone.sdk.entity.paymentmethod.Transfer
import io.portone.sdk.entity.paymentmethod.VirtualAccount
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class PaymentRequest(
    val storeId: String, // 스토어 아이디
    val paymentId: String, // 주문 번호
    val orderName: String, // 주문명
    val totalAmount: Int, // 결제 금액(실제 결제 금액 X 10^ 해당 currency의 scale factor, 예) $1.50 -> 150)
    val payMethod: PayMethod,
    val channelKey: String? = null, // 채널 이름
    val pgProvider: PgProvider? = null, // PG사
    val isTestChannel: Boolean? = null, // 테스트 채널 여부
    val channelGroupId: String? = null, // 채널 그룹 아이디
    val taxFreeAmount: Int? = null, // 면세 금액
    val vatAmount: Int? = null, // 부가세
    val customer: Customer? = null, // 구매자 정보
    val windowType: WindowType? = null, // 결제창 유형
    private val redirectUrl: String? = PortOne.REDIRECT_URL, //  결제 프로세스 종료 후 리디렉션 될 URL
    val noticeUrls: List<String>? = null, // 웹훅 URL
    val confirmUrl: String? = null, // Confirm URL
    val appScheme: String? = null, // 앱 URL Scheme
    val isEscrow: Boolean? = null, // 에스크로 결제 여부
    val products: List<Product>? = null, // 에스크로 정보
    val isCulturalExpense: Boolean? = null, // 문화비 지출 여부
    val currency: Currency, // 통화
    val locale: Locale? = null, // 결제창 언어
    val customData: String? = null, // 가맹점 Custom Data
    val offerPeriod: OfferPeriod? = null,
    val productType: ProductType? = null,
    val storeDetails: StoreDetails? = null,
    val country: Country? = null,
    val shippingAddress: Address? = null, // 배송지 주소
    val promotionId: String? = null, // 프로모션 아이디
    val bypass: String? = null, // TODO 작업 필요
    val card: Card? = null,
    val virtualAccount: VirtualAccount? = null,
    val transfer: Transfer? = null,
    val mobile: Mobile? = null,
    val giftCertificate: GiftCertificate? = null,
    val easyPay: EasyPay? = null,
) : Parcelable
