package io.portone.sdk.__generated__.entity.bypass

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.bypass.payment.EximbayV2Bypass
import io.portone.sdk.__generated__.entity.bypass.payment.HyphenBypass
import io.portone.sdk.__generated__.entity.bypass.payment.InicisJpBypass
import io.portone.sdk.__generated__.entity.bypass.payment.InicisV2Bypass
import io.portone.sdk.__generated__.entity.bypass.payment.KakaopayPaymentBypass
import io.portone.sdk.__generated__.entity.bypass.payment.KcpV2Bypass
import io.portone.sdk.__generated__.entity.bypass.payment.KpnBypass
import io.portone.sdk.__generated__.entity.bypass.payment.KsnetPaymentBypass
import io.portone.sdk.__generated__.entity.bypass.payment.NaverpayPaymentBypass
import io.portone.sdk.__generated__.entity.bypass.payment.NiceV2PaymentBypass
import io.portone.sdk.__generated__.entity.bypass.payment.PayletterGlobalBypass
import io.portone.sdk.__generated__.entity.bypass.payment.PaypalV2PaymentBypass
import io.portone.sdk.__generated__.entity.bypass.payment.SmartroV2PaymentBypass
import io.portone.sdk.__generated__.entity.bypass.payment.TossBrandpayPaymentBypass
import io.portone.sdk.__generated__.entity.bypass.payment.TosspayV2PaymentBypass
import io.portone.sdk.__generated__.entity.bypass.payment.TosspaymentsPaymentBypass
import io.portone.sdk.__generated__.entity.bypass.payment.WelcomePaymentBypass
import kotlinx.parcelize.Parcelize

/**
 * **PG사 결제창 호출 시 PG사로 그대로 bypass할 값들의 모음**
 */
@Parcelize
data class PaymentBypass(
    /**
     * 토스페이먼츠 bypass 파라미터
     */
    val tosspayments: TosspaymentsPaymentBypass?,
    /**
     * KSNET bypass 파라미터
     */
    val ksnet: KsnetPaymentBypass?,
    /**
     * **Paypal bypass 파라미터**
     */
    val paypalV2: PaypalV2PaymentBypass?,
    /**
     * 카카오페이 bypass 파라미터
     */
    val kakaopay: KakaopayPaymentBypass?,
    /**
     * 스마트로 V2 bypass 파라미터
     */
    val smartroV2: SmartroV2PaymentBypass?,
    /**
     * 네이버페이 bypass 파라미터
     */
    val naverpay: NaverpayPaymentBypass?,
    /**
     * (신)나이스페이먼츠 bypass 파라미터
     */
    val niceV2: NiceV2PaymentBypass?,
    /**
     * 토스 브랜드페이 bypass 파라미터
     */
    val tossBrandpay: TossBrandpayPaymentBypass?,
    /**
     * 웰컴페이먼츠 bypass 파라미터
     */
    val welcome: WelcomePaymentBypass?,
    /**
     * 토스페이 bypass 파라미터
     */
    val tosspayV2: TosspayV2PaymentBypass?,
    /**
     * KG이니시스 bypass 파라미터
     * 
     * KG이니시스는 PC 결제 모듈과 모바일 결제 모듈이 분리되어 있기 때문에 bypass 파라미터 또한 PC용과 모바일용이 분리되어 있습니다.
     */
    val inicisV2: InicisV2Bypass?,
    /**
     * KPN bypass 파라미터
     */
    val kpn: KpnBypass?,
    /**
     * NHN KCP bypass 파라미터
     */
    val kcpV2: KcpV2Bypass?,
    /**
     * 하이픈 bypass 파라미터
     */
    val hyphen: HyphenBypass?,
    /**
     * 엑심베이 V2 bypass 파라미터
     */
    val eximbayV2: EximbayV2Bypass?,
    /**
     * 이니시스 일본 bypass 파라미터
     */
    val inicisJp: InicisJpBypass?,
    /**
     * 페이레터 해외결제 bypass 파라미터
     */
    val payletterGlobal: PayletterGlobalBypass?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "tosspayments" to tosspayments?.let { tosspayments.toJson() },
        "ksnet" to ksnet?.let { ksnet.toJson() },
        "paypal_v2" to paypalV2?.let { paypalV2.toJson() },
        "kakaopay" to kakaopay?.let { kakaopay.toJson() },
        "smartro_v2" to smartroV2?.let { smartroV2.toJson() },
        "naverpay" to naverpay?.let { naverpay.toJson() },
        "nice_v2" to niceV2?.let { niceV2.toJson() },
        "toss_brandpay" to tossBrandpay?.let { tossBrandpay.toJson() },
        "welcome" to welcome?.let { welcome.toJson() },
        "tosspay_v2" to tosspayV2?.let { tosspayV2.toJson() },
        "inicis_v2" to inicisV2?.let { inicisV2.toJson() },
        "kpn" to kpn?.let { kpn.toJson() },
        "kcp_v2" to kcpV2?.let { kcpV2.toJson() },
        "hyphen" to hyphen?.let { hyphen.toJson() },
        "eximbay_v2" to eximbayV2?.let { eximbayV2.toJson() },
        "inicis_jp" to inicisJp?.let { inicisJp.toJson() },
        "payletter_global" to payletterGlobal?.let { payletterGlobal.toJson() }
    )
}
