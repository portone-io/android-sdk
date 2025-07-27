package io.portone.sdk.__generated__.entity

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.IssueBillingKeyUIType
import io.portone.sdk.__generated__.entity.PaymentUIType
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class LoadableUIType : Parcelable {
    @Parcelize
    data class PaymentUIType(val value: PaymentUIType) : LoadableUIType()
    @Parcelize
    data class IssueBillingKeyUIType(val value: IssueBillingKeyUIType) : LoadableUIType()

    fun toJson(): Any = when (this) {
        is PaymentUIType -> value.toJson()
        is IssueBillingKeyUIType -> value.toJson()
    }
}
