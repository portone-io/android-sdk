package io.portone.sdk.android.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Product(
    val id: String,
    val name: String,
    val code: String? = null,
    val amount: Long,
    val quantity: Int,
    val tag: String? = null,
): Parcelable
