package io.portone.sdk.__generated__.entity

import io.portone.sdk.__generated__.entity.IssueBillingKeyUIType
import io.portone.sdk.__generated__.entity.PaymentUIType

sealed class LoadableUIType {
    data class PaymentUIType(val value: PaymentUIType) : LoadableUIType()
    data class IssueBillingKeyUIType(val value: IssueBillingKeyUIType) : LoadableUIType()

    fun toJson(): Any = when (this) {
        is PaymentUIType -> value.toJson()
        is IssueBillingKeyUIType -> value.toJson()
    }
}
