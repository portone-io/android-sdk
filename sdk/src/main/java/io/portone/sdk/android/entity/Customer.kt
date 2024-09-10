package io.portone.sdk.android.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Customer(
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
): Parcelable
