package io.portone.sdk.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Product(
    val id: String,
    val name: String,
    val code: String,
    val unitPrice: Long,
    val quantity: Int,
    val tag: String?
): Parcelable
