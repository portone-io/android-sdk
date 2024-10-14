package io.portone.sdk.android.request.type

import io.portone.sdk.android.type.Country
import kotlinx.serialization.Serializable

@Serializable
internal data class Address(
    val country: Country? = null,
    val addressLine1: String,
    val addressLine2: String,
    val city: String? = null,
    val province: String? = null,
)
