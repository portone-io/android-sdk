package io.portone.sdk.__generated__.entity.bypass.loadPaymentUI

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaypalV2AdditionalData(
    val key: String,
    val `value`: String
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "key" to key,
        "value" to `value`
    )
}
