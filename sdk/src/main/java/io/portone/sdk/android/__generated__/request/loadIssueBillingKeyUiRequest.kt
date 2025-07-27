package io.portone.sdk.__generated__.request

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.BillingKeyMethod
import io.portone.sdk.__generated__.entity.Currency
import io.portone.sdk.__generated__.entity.Customer
import io.portone.sdk.__generated__.entity.IssueBillingKeyUIType
import io.portone.sdk.__generated__.entity.Locale
import io.portone.sdk.__generated__.entity.ProductType
import io.portone.sdk.__generated__.entity.bypass.LoadIssueBillingKeyUIBypass
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoadIssueBillingKeyUIRequest(
    val uiType: IssueBillingKeyUIType,
    /**
     * 빌링 등록 UI에 표시되는 금액
     */
    val displayAmount: Long?,
    /**
     * **화폐**
     * 
     * [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217) 화폐 코드
     * 
     * 대한민국 원, 일본 엔이 아닌 화폐를 사용할 때에는 금액을 minor unit 단위로 입력해야 함에 유의하세요.
     */
    val currency: Currency?,
    /**
     * **상점 아이디**
     * 
     * 포트원 계정에 생성된 상점을 식별하는 고유한 값으로 [관리자 콘솔 > 연동 정보](https://admin.portone.io/integration-v2/manage/channel) 우측 상단에서 확인할 수 있습니다.
     */
    val storeId: String,
    /**
     * **채널 키**
     * 
     * 포트원에 등록된 결제 채널 중 하나를 지정합니다.
     * 
     * [관리자 콘솔 > 연동 정보](https://admin.portone.io/integration-v2/manage/channel)에서 채널 연동 후 채널 키를 확인할 수 있습니다.
     * 
     * 채널 키와 채널 그룹 ID 중 하나를 지정해야 합니다.
     */
    val channelKey: String,
    /**
     * 빌링키 발급 수단
     */
    val billingKeyMethod: BillingKeyMethod,
    /**
     * **주문명**
     */
    val issueName: String?,
    /**
     * **빌링 등록 주문 번호**
     */
    val issueId: String?,
    val customer: Customer?,
    /**
     * **UI 언어**
     * 
     * KG이니시스, 스마트로, KSNET, 웰컴페이먼츠 (PC), 한국결제네트웍스, 엑심베이에서 설정 가능하며, PG마다 지원하는 언어 목록은 차이가 있습니다.
     */
    val locale: Locale?,
    /**
     * **빌링키 커스텀 JSON 데이터**
     * 
     * 자유롭게 데이터를 넣어 이후 조회할 수 있습니다.
     */
    val customData: Map<String, Any?>?,
    /**
     * **앱 URL 스킴**
     */
    val appScheme: String,
    /**
     * **웹훅 URL**
     */
    val noticeUrls: List<String>?,
    /**
     * **상품 유형**
     */
    val productType: ProductType?,
    val bypass: LoadIssueBillingKeyUIBypass?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "uiType" to uiType.toJson(),
        "displayAmount" to displayAmount?.let { displayAmount },
        "currency" to currency?.let { currency.toJson() },
        "storeId" to storeId,
        "channelKey" to channelKey,
        "billingKeyMethod" to billingKeyMethod.toJson(),
        "issueName" to issueName?.let { issueName },
        "issueId" to issueId?.let { issueId },
        "customer" to customer?.let { customer.toJson() },
        "locale" to locale?.let { locale.toJson() },
        "customData" to customData?.let { customData },
        "appScheme" to appScheme,
        "noticeUrls" to noticeUrls?.let { noticeUrls },
        "productType" to productType?.let { productType.toJson() },
        "bypass" to bypass?.let { bypass.toJson() }
    )
}
