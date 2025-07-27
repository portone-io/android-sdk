package io.portone.sdk.__generated__.request

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.CardCompany
import io.portone.sdk.__generated__.entity.CashReceiptType
import io.portone.sdk.__generated__.entity.EasyPayPaymentMethod
import io.portone.sdk.__generated__.entity.EasyPayProvider
import io.portone.sdk.__generated__.entity.Installment
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentRequestUnionEasyPay(
    /**
     * PG 제휴로 간편결제를 이용할 때, 간편결제 UI를 직접 호출할 수 있는 간편결제
     */
    val easyPayProvider: EasyPayProvider?,
    /**
     * **상점분담 무이자 활성화 여부**
     */
    val useFreeInterestFromMall: Boolean?,
    /**
     * **일부 카드사만 노출 설정**
     * 
     * 일부 카드사만을 선택 가능하게 하고 싶은 경우 사용하는 옵션입니다.
     */
    val availableCards: List<CardCompany>?,
    /**
     * **할부 설정**
     * 
     * 신용카드 결제 시 할부 관련 설정을 제어합니다.
     * 
     * ## 사용 예시
     * 
     * ### 3개월 고정 할부
     * 
     * ```typescript
     * installment: {
     * monthOption: {
     * fixedMonth: 3
     * }
     * }
     * ```
     * 
     * ### 2~6개월 할부 선택
     * 
     * ```typescript
     * installment: {
     * monthOption: {
     * availableMonthList: [2, 3, 4, 5, 6]
     * }
     * }
     * ```
     * 
     * ### 무이자 할부 설정 (삼성카드 2,3개월)
     * 
     * ```typescript
     * installment: {
     * freeInstallmentPlans: [{
     * cardCompany: 'CARD_COMPANY_SAMSUNG',
     * months: [2, 3]
     * }]
     * }
     * ```
     * 
     * ## 주의사항
     * 
     * - 일반적으로 5만원 이상부터 할부가 가능합니다
     * - 1개월 할부는 일시불과 동일하게 처리됩니다
     * - 카드 다이렉트 호출 시 고정 할부만 가능한 PG사가 있습니다
     * - 무이자 할부는 가맹점이 수수료를 부담하는 방식입니다
     */
    val installment: Installment?,
    /**
     * **현금영수증 발급 유형**
     */
    val cashReceiptType: CashReceiptType?,
    /**
     * **현금영수증 구매자 번호**
     * 
     * 카드일련번호, 주민등록번호, 사업자등록번호, 휴대전화번호 중 하나입니다.
     * 
     * `cashReceiptType`이 있고 `ANONYMOUS`가 아닐 때 필수입니다.
     */
    val customerIdentifier: String?,
    /**
     * **카드사 포인트 사용 여부**
     * 
     * 토스페이먼츠 브랜드페이에서 사용합니다.
     * `true`로 설정하면 카드사 포인트 사용이 켜진 상태로 UI가 표시됩니다.
     * 이외의 경우 구매자가 카드사 포인트 사용 여부를 선택할 수 있습니다.
     * 토스페이먼츠와의 추가 게약이 필요합니다.
     */
    val useCardPoint: Boolean?,
    /**
     * **표시할 간편결제 수단 목록**
     * 
     * 나이스페이먼츠, NHN KCP, KSNET에서 일부 간편결제의 UI를 직접 호출할 때 사용합니다.
     * 
     * PG 제휴를 통해 간편결제를 이용할 때에는 복합결제가 불가능한 경우가 많습니다. 즉, 전액 카드 결제나 전액 포인트 결제 등만 가능합니다.
     * 보통 UI 안에서 결제 수단을 선택하게 되나, 일부 PG에서는 UI를 호출할 때 수단 중 하나를 지정할 수 있는 기능을 제공하기도 합니다.
     * 이때 `availablePayMethods`가 사용됩니다.
     * 
     * 단, 나이스페이먼츠를 통해 네이버페이 UI를 직접 호출할 때에는 **필수**임에 유의합니다.
     */
    val availablePayMethods: List<EasyPayPaymentMethod>?,
    /**
     * **할부 사용 가능 여부**
     * 
     * `false`로 지정하면 신용카드 할부 사용을 금지합니다. 토스페이(직계약)에서 지원합니다.
     */
    val useInstallment: Boolean?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "easyPayProvider" to easyPayProvider?.let { easyPayProvider.toJson() },
        "useFreeInterestFromMall" to useFreeInterestFromMall?.let { useFreeInterestFromMall },
        "availableCards" to availableCards?.let { availableCards.map { it.toJson() } },
        "installment" to installment?.let { installment.toJson() },
        "cashReceiptType" to cashReceiptType?.let { cashReceiptType.toJson() },
        "customerIdentifier" to customerIdentifier?.let { customerIdentifier },
        "useCardPoint" to useCardPoint?.let { useCardPoint },
        "availablePayMethods" to availablePayMethods?.let { availablePayMethods.map { it.toJson() } },
        "useInstallment" to useInstallment?.let { useInstallment }
    )
}
