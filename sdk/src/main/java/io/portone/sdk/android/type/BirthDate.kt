package io.portone.sdk.android.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class BirthDate(
    val birthYear: Int? = null,
    val birthMonth: Int? = null,
    val birthDay: Int? = null,
): Parcelable
