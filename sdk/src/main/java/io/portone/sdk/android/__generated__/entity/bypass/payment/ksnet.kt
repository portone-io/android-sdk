package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.payment.KsnetSndQpayType
import kotlinx.parcelize.Parcelize

/**
 * KSNET bypass 파라미터
 */
@Parcelize
data class KsnetPaymentBypass(
    /**
     * 간편 결제 표시 구분
     */
    val sndQpayType: KsnetSndQpayType?,
    /**
     * **KSNET 간편결제 다이렉트 여부**
     */
    val easyPayDirect: Boolean?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "sndQpayType" to sndQpayType?.let { sndQpayType.toJson() },
        "easyPayDirect" to easyPayDirect?.let { easyPayDirect }
    )
}
