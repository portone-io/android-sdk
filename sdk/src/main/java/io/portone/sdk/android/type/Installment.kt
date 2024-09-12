package io.portone.sdk.android.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Installment(
    val monthOption: MonthOption?,
    val freeInstallmentPlans: List<FreeInstallmentPlan>?
) : Parcelable {
    companion object {
        @Serializable
        @Parcelize
        sealed interface MonthOption : Parcelable {
            @Serializable
            @Parcelize
            data class FixedMonth(val month: Int) : MonthOption

            @Serializable
            @Parcelize
            data class AvailableMonths(val months: List<Int>) : MonthOption
        }

        @Serializable
        @Parcelize
        data class FreeInstallmentPlan(
            val months: List<Int>,
            val cardCompany: CardCompany
        ) : Parcelable
    }
}
