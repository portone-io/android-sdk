package io.portone.sdk.entity.paymentmethod

import android.os.Parcelable
import io.portone.sdk.entity.GiftCertificateType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GiftCertificate(
    val giftCertificateType: GiftCertificateType? = null,
): Parcelable, PaymentMethod