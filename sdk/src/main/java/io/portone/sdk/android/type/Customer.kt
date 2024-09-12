package io.portone.sdk.android.type

import android.os.Parcelable
import io.portone.sdk.android.request.type.Customer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Customer(
    val id: String? = null,
    val name: Name? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val address: Address? = null,
    val gender: Gender? = null,
    val birthDate: BirthDate? = null,
) : Parcelable {
    @Serializable
    @Parcelize
    sealed interface Name : Parcelable {
        @Serializable
        @Parcelize
        data class Full(
            val fullName: String
        ) : Name

        @Serializable
        @Parcelize
        data class Separated(
            val firstName: String? = null,
            val lastName: String? = null,
        ) : Name
    }

    internal fun toRequest(): Customer = Customer(
        customerId = id,
        fullName = when (name) {
            is Name.Full -> {
                name.fullName
            }

            is Name.Separated,
            null -> null
        },
        firstName = when (name) {
            is Name.Separated -> {
                name.firstName
            }

            is Name.Full,
            null -> null
        },
        lastName = when (name) {
            is Name.Separated -> {
                name.lastName
            }
            is Name.Full,
            null -> null
        },
        phoneNumber = phoneNumber,
        email = email,
        address = address,
        zipcode = address?.zipcode,
        gender = gender,
        birthYear = birthDate?.birthYear?.toString(),
        birthMonth = birthDate?.birthMonth?.toString(),
        birthDay = birthDate?.birthYear?.toString(),
    )

}
