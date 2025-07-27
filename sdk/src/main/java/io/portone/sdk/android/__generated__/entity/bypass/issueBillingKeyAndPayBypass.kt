package io.portone.sdk.__generated__.entity.bypass

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.issueBillingKeyAndPay.EximbayV2IssueBillingKeyAndPayBypass
import io.portone.sdk.__generated__.entity.bypass.issueBillingKeyAndPay.PayletterGlobalIssueBillingKeyAndPayBypass
import io.portone.sdk.__generated__.entity.bypass.issueBillingKeyAndPay.WelcomeIssueBillingKeyAndPayBypass
import kotlinx.parcelize.Parcelize

@Parcelize
data class IssueBillingKeyAndPayBypass(
    /**
     * **웰컴페이먼츠 bypass 파라미터**
     */
    val welcome: WelcomeIssueBillingKeyAndPayBypass?,
    /**
     * **페이레터 해외결제 bypass 파라미터**
     */
    val payletterGlobal: PayletterGlobalIssueBillingKeyAndPayBypass?,
    /**
     * **엑심베이 bypass 파라미터**
     */
    val eximbayV2: EximbayV2IssueBillingKeyAndPayBypass?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "welcome" to welcome?.let { welcome.toJson() },
        "payletter_global" to payletterGlobal?.let { payletterGlobal.toJson() },
        "eximbay_v2" to eximbayV2?.let { eximbayV2.toJson() }
    )
}
