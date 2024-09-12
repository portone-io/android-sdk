package io.portone.sdk.android.identityverification

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class IdentityVerificationBypass(
    val danal: Danal?,
) : Parcelable {
    @Parcelize
    @Serializable
    data class Danal(
        val CPTITLE: String? = null,
        val AGELIMIT: Int? = null,
        val IsCarrier: String? = null
    ) : Parcelable
}