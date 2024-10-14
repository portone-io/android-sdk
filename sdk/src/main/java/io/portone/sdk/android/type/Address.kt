package io.portone.sdk.android.type

import android.os.Parcelable
import io.portone.sdk.android.request.type.Address
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Address(
    val country: Country? = null,
    val addressLine1: String,
    val addressLine2: String,
    val city: String? = null,
    val province: String? = null,
    val zipcode: String? = null,
) : Parcelable {
    internal fun toRequest(): Address {
        return Address(
            country = country,
            addressLine1 = addressLine1,
            addressLine2 = addressLine2,
            city = city,
            province = province,
        )
    }
}
