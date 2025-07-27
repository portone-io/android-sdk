package io.portone.sdk.__generated__.request

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.CardCompany
import io.portone.sdk.__generated__.entity.EasyPayPaymentMethod
import io.portone.sdk.__generated__.entity.EasyPayProvider
import kotlinx.parcelize.Parcelize

@Parcelize
data class IssueBillingKeyRequestUnionEasyPay(
    val availableCards: List<CardCompany>?,
    /**
     * PG 제휴로 간편결제를 이용할 때, 간편결제 UI를 직접 호출할 수 있는 간편결제
     */
    val easyPayProvider: EasyPayProvider?,
    /**
     * 노출을 허용할 결제 수단의 종류
     */
    val availablePayMethods: List<EasyPayPaymentMethod>?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "availableCards" to availableCards?.let { availableCards.map { it.toJson() } },
        "easyPayProvider" to easyPayProvider?.let { easyPayProvider.toJson() },
        "availablePayMethods" to availablePayMethods?.let { availablePayMethods.map { it.toJson() } }
    )
}
