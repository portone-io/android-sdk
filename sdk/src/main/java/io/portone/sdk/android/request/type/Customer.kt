package io.portone.sdk.android.request.type

import io.portone.sdk.android.type.Address
import io.portone.sdk.android.type.Gender
import kotlinx.serialization.Serializable

@Serializable
internal data class Customer(
    val customerId: String? = null,
    val fullName: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val address: Address? = null,
    val zipcode: String? = null,
    val gender: Gender? = null,
    val birthYear: String? = null,
    val birthMonth: String? = null,
    val birthDay: String? = null
)
