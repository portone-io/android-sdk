package io.portone.sdk.android.request.type

import android.os.Parcelable
import io.portone.sdk.android.type.Country
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
internal data class Address(
    val country: Country? = null,
    val addressLine1: String,
    val addressLine2: String,
    val city: String? = null,
    val province: String? = null,
) : Parcelable
