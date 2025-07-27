package io.portone.sdk.__generated__.request

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.Address
import io.portone.sdk.__generated__.entity.Country
import io.portone.sdk.__generated__.entity.Currency
import io.portone.sdk.__generated__.entity.Customer
import io.portone.sdk.__generated__.entity.Iframe
import io.portone.sdk.__generated__.entity.Locale
import io.portone.sdk.__generated__.entity.OfferPeriod
import io.portone.sdk.__generated__.entity.PaymentPayMethod
import io.portone.sdk.__generated__.entity.Popup
import io.portone.sdk.__generated__.entity.Product
import io.portone.sdk.__generated__.entity.ProductType
import io.portone.sdk.__generated__.entity.StoreDetails
import io.portone.sdk.__generated__.entity.WindowTypes
import io.portone.sdk.__generated__.entity.bypass.PaymentBypass
import io.portone.sdk.__generated__.request.PaymentRequestUnionAlipay
import io.portone.sdk.__generated__.request.PaymentRequestUnionCard
import io.portone.sdk.__generated__.request.PaymentRequestUnionConvenienceStore
import io.portone.sdk.__generated__.request.PaymentRequestUnionEasyPay
import io.portone.sdk.__generated__.request.PaymentRequestUnionGiftCertificate
import io.portone.sdk.__generated__.request.PaymentRequestUnionMobile
import io.portone.sdk.__generated__.request.PaymentRequestUnionPaypal
import io.portone.sdk.__generated__.request.PaymentRequestUnionTransfer
import io.portone.sdk.__generated__.request.PaymentRequestUnionVirtualAccount
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentRequest(
    /**
     * **상점 아이디**
     * 
     * 포트원 계정에 생성된 상점을 식별하는 고유한 값으로 [관리자 콘솔 > 연동 정보](https://admin.portone.io/integration-v2/manage/channel) 우측 상단에서 확인할 수 있습니다.
     */
    val storeId: String,
    /**
     * **결제 ID**
     * 
     * 고객사에서 임의로 ID를 정합니다.
     * 
     * 이미 결제 완료된 `paymentId`로 결제를 요청하는 경우 실패합니다.
     */
    val paymentId: String,
    /**
     * **주문명**
     */
    val orderName: String,
    /**
     * **결제 금액**
     * 
     * 결제 금액을 정수로 나타냅니다.
     * 
     * 해외 통화의 경우 통화의 최소 단위(minor unit)를 기준으로 합니다. 예를 들어, USD의 최소 단위는 센트(0.01 USD)이므로, 6 USD의 경우 100배하여 600으로 입력합니다.
     * 
     * 최소 단위는 [ISO 4217](https://www.iso.org/iso-4217-currency-codes.html)에 표준화된 것을 기준으로 합니다.
     * 
     * - KRW: 1배
     * - USD: 100배
     * - JPY: 1배
     */
    val totalAmount: Long,
    /**
     * **화폐**
     * 
     * [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217) 화폐 코드
     * 
     * 대한민국 원, 일본 엔이 아닌 화폐를 사용할 때에는 금액을 minor unit 단위로 입력해야 함에 유의하세요.
     */
    val currency: Currency,
    /**
     * **결제수단 구분코드**
     * 
     * PG사별 지원되는 결제수단이 모두 상이합니다.
     * 
     * [각 PG사별 결제 연동 가이드](https://developers.portone.io/opi/ko/integration/pg/v2/readme?v=v2)를 참고하세요
     */
    val payMethod: PaymentPayMethod,
    /**
     * **채널 키**
     * 
     * 포트원에 등록된 결제 채널 중 하나를 지정합니다.
     * 
     * [관리자 콘솔 > 연동 정보](https://admin.portone.io/integration-v2/manage/channel)에서 채널 연동 후 채널 키를 확인할 수 있습니다.
     * 
     * 채널 키와 채널 그룹 ID 중 하나를 지정해야 합니다.
     */
    val channelKey: String?,
    /**
     * **채널 그룹 ID**
     * 
     * 채널 그룹 ID를 지정하여 결제창을 호출하면, 채널 그룹 내 설정된 비율에 따라 확률적으로 하나의 채널이 선택됩니다. [관리자 콘솔 > 연동 관리 > 스마트 라우팅](https://admin.portone.io/integration-v2/smart-routing)에서 설정합니다.
     * 
     * 채널 키와 채널 그룹 ID 중 하나를 지정해야 합니다.
     */
    val channelGroupId: String?,
    /**
     * **면세 금액**
     * 
     * 미입력 시 0으로 취급됩니다.
     */
    val taxFreeAmount: Long?,
    /**
     * **부가세 금액**
     * 
     * 미입력 시 과세 금액의 1/11로 자동 계산됩니다.
     */
    val vatAmount: Long?,
    val customer: Customer?,
    /**
     * **환경 별 제공되는 결제/본인인증 창 유형**
     * 
     * - PG사에 따라 가능한 창 유형이 다릅니다.
     * - 전달되지 않았을 때 결정되는 기본 창이 다릅니다.
     * - 미입력 시, 해당 PG사의 기본 창 방식을 따릅니다.
     */
    val windowType: WindowTypes?,
    /**
     * **리디렉션 방식에서 결제 완료 후 이동할 URL**
     * 
     * 결제사 페이지로 이동하여 진행하는 리디렉션 방식의 경우 필수로 설정해야 합니다. 대부분의 모바일 환경이 리디렉션 방식에 해당됩니다.
     */
    val redirectUrl: String?,
    /**
     * **웹훅 수신 URL**
     * 
     * 포트원 관리자 콘솔에 설정한 웹훅 URL 대신 사용할 웹훅 URL을 결제시마다 설정할 수 있습니다.
     * 
     * 올바른 HTTP(S) URL이어야 합니다.
     */
    val noticeUrls: List<String>?,
    /**
     * **결제 승인 여부 확인 URL**
     * 
     * 컨펌 프로세스 웹훅을 수신할 URL입니다.
     * 
     * 올바른 HTTP(S) URL이어야 합니다.
     * 
     * 별도 요청이 필요합니다. (<tech.support@portone.io>)
     */
    val confirmUrl: String?,
    /**
     * **모바일 결제 후 고객사 앱으로 복귀를 위한 URL scheme**
     * 
     * - WebView 환경 결제시 필수설정 항목 입니다.
     * - ISP/앱카드 앱에서 결제정보인증 후 기존 앱으로 복귀할 때 사용합니다.
     */
    val appScheme: String?,
    /**
     * **에스크로 결제 여부**
     * 
     * 미입력 시 기본값: `false`
     * 
     * - 에스크로 설정은 PG사와 협의 이후 진행되어야 합니다.
     */
    val isEscrow: Boolean?,
    /**
     * **구매 상품 정보**
     */
    val products: List<Product>?,
    /**
     * **문화비 지출 여부**
     * 
     * 도서, 공연, 박물관 등 문화비 지출 여부
     */
    val isCulturalExpense: Boolean?,
    /**
     * **UI 언어**
     * 
     * KG이니시스, 스마트로, KSNET, 웰컴페이먼츠 (PC), 한국결제네트웍스, 엑심베이에서 설정 가능하며, PG마다 지원하는 언어 목록은 차이가 있습니다.
     */
    val locale: Locale?,
    /**
     * **결제 정보에 포함할 고객사 커스텀 JSON 데이터**
     */
    val customData: Map<String, Any?>?,
    /**
     * **PG사 결제창 호출 시 PG사로 그대로 bypass할 값들의 모음**
     */
    val bypass: PaymentBypass?,
    /**
     * **국가**
     * 
     * [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) 코드입니다.
     */
    val country: Country?,
    /**
     * **상품 유형**
     */
    val productType: ProductType?,
    /**
     * **서비스 제공 기간**
     * 
     * range와 interval 중 하나를 입력해주세요.
     * 
     * - range: 제공 기간 범위
     * - interval: 제공 기간 주기
     * 
     * 예1) 2023년 1월 1일 00시 00분 00초(KST) ~
     * 
     * ```js
     * range: {
     * from: '2023-01-01T00:00:00+09:00'
     * }
     * ```
     * 
     * 예2) ~ 2023년 1월 1일 00시 00분 00초(KST)
     * 
     * ```js
     * range: {
     * to: '2023-01-01T00:00:00+09:00'
     * }
     * ```
     * 
     * 예3) 2023년 1월 1일 00시 00분 00초(KST) ~ 2023년 12월 31일 23시 59분 59초(KST)
     * 
     * ```js
     * range: {
     * from: '2023-12-31T23:59:59+09:00'
     * to: '2023-01-01T00:00:00+09:00'
     * }
     * ```
     * 
     * 예4) 30일 주기
     * `interval: '30d'`
     * 
     * 예5) 6개월 주기
     * `interval: '6m'`
     * 
     * 예6) 1년 주기
     * `interval: '1y'`
     */
    val offerPeriod: OfferPeriod?,
    /**
     * **상점 정보**
     * 
     * - KSNET 카카오페이의 경우 필수 입력
     * - 나이스페이먼츠의 경우 매출 전표에 표기 할 용도로 선택 입력
     * - KG이니시스 일본결제의 경우 JPPG(gmoPayment) 결제의 상점정보로 사용되거나 편의점 결제 시 영수증 표시 정보로 사용됨.
     */
    val storeDetails: StoreDetails?,
    /**
     * **주소 정보**
     */
    val shippingAddress: Address?,
    /**
     * **프로모션 아이디**
     * 
     * 포트원의 프로모션 기능 이용시 지정합니다.
     */
    val promotionId: String?,
    /**
     * **팝업 관련 필드**
     * 
     * UI가 팝업 창으로 열릴 때 적용되는 속성입니다.
     */
    val popup: Popup?,
    /**
     * **결제창이 iframe 방식일 경우 결제창에 적용할 속성**
     */
    val iframe: Iframe?,
    /**
     * **카드 결제 설정**
     */
    val card: PaymentRequestUnionCard?,
    /**
     * **가상계좌 결제 설정**
     */
    val virtualAccount: PaymentRequestUnionVirtualAccount?,
    /**
     * **계좌이체 결제 설정**
     */
    val transfer: PaymentRequestUnionTransfer?,
    /**
     * **휴대전화 결제 설정**
     */
    val mobile: PaymentRequestUnionMobile?,
    /**
     * **상품권 결제 설정**
     */
    val giftCertificate: PaymentRequestUnionGiftCertificate?,
    val easyPay: PaymentRequestUnionEasyPay?,
    val paypal: PaymentRequestUnionPaypal?,
    val alipay: PaymentRequestUnionAlipay?,
    val convenienceStore: PaymentRequestUnionConvenienceStore?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "storeId" to storeId,
        "paymentId" to paymentId,
        "orderName" to orderName,
        "totalAmount" to totalAmount,
        "currency" to currency.toJson(),
        "payMethod" to payMethod.toJson(),
        "channelKey" to channelKey?.let { channelKey },
        "channelGroupId" to channelGroupId?.let { channelGroupId },
        "taxFreeAmount" to taxFreeAmount?.let { taxFreeAmount },
        "vatAmount" to vatAmount?.let { vatAmount },
        "customer" to customer?.let { customer.toJson() },
        "windowType" to windowType?.let { windowType.toJson() },
        "redirectUrl" to redirectUrl?.let { redirectUrl },
        "noticeUrls" to noticeUrls?.let { noticeUrls },
        "confirmUrl" to confirmUrl?.let { confirmUrl },
        "appScheme" to appScheme?.let { appScheme },
        "isEscrow" to isEscrow?.let { isEscrow },
        "products" to products?.let { products.map { it.toJson() } },
        "isCulturalExpense" to isCulturalExpense?.let { isCulturalExpense },
        "locale" to locale?.let { locale.toJson() },
        "customData" to customData?.let { customData },
        "bypass" to bypass?.let { bypass.toJson() },
        "country" to country?.let { country.toJson() },
        "productType" to productType?.let { productType.toJson() },
        "offerPeriod" to offerPeriod?.let { offerPeriod.toJson() },
        "storeDetails" to storeDetails?.let { storeDetails.toJson() },
        "shippingAddress" to shippingAddress?.let { shippingAddress.toJson() },
        "promotionId" to promotionId?.let { promotionId },
        "popup" to popup?.let { popup.toJson() },
        "iframe" to iframe?.let { iframe.toJson() },
        "card" to card?.let { card.toJson() },
        "virtualAccount" to virtualAccount?.let { virtualAccount.toJson() },
        "transfer" to transfer?.let { transfer.toJson() },
        "mobile" to mobile?.let { mobile.toJson() },
        "giftCertificate" to giftCertificate?.let { giftCertificate.toJson() },
        "easyPay" to easyPay?.let { easyPay.toJson() },
        "paypal" to paypal?.let { paypal.toJson() },
        "alipay" to alipay?.let { alipay.toJson() },
        "convenienceStore" to convenienceStore?.let { convenienceStore.toJson() }
    )
}
