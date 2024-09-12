package io.portone.sdk.android.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Amount(
    val total: Long,
    val taxFree: Long? = null,
    val vat: Long? = null,
    val currency: Currency
): Parcelable
