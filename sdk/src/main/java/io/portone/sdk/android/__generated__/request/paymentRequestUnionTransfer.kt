package io.portone.sdk.__generated__.request

import io.portone.sdk.__generated__.entity.Bank
import io.portone.sdk.__generated__.entity.CashReceiptType

/**
 * **계좌이체 결제 설정**
 */
data class PaymentRequestUnionTransfer(
    /**
     * **현금영수증 발급 유형**
     */
    val cashReceiptType: CashReceiptType?,
    /**
     * **현금영수증 구매자 번호**
     * 
     * 카드일련번호, 주민등록번호, 사업자등록번호, 휴대전화번호 중 하나입니다. NICE페이먼츠와 스마트로에서 PG UI를 건너뛸 때 사용합니다.
     */
    val customerIdentifier: String?,
    /**
     * 가상계좌 발급시 사용되는 은행 코드
     */
    val bankCode: Bank?
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "cashReceiptType" to cashReceiptType?.let { cashReceiptType.toJson() },
        "customerIdentifier" to customerIdentifier?.let { customerIdentifier },
        "bankCode" to bankCode?.let { bankCode.toJson() }
    )
}
