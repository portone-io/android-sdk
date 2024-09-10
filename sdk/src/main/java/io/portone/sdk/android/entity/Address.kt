package io.portone.sdk.android.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Address(
    val country: Country? = null,
    val addressLine1: String,
    val addressLine2: String,
    val city: String? = null,
    val province: String? = null
): Parcelable
