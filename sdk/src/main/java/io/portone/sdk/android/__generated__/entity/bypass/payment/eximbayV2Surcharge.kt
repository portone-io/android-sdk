package io.portone.sdk.__generated__.entity.bypass.payment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EximbayV2Surcharge(
    /**
     * 항목명
     */
    val name: String?,
    /**
     * 수량
     */
    val quantity: String?,
    /**
     * 단가 (음수 가능)
     */
    val unitPrice: String?
) : Parcelable {
    fun toJson(): Map<String, Any?> = mapOf(
        "name" to name?.let { name },
        "quantity" to quantity?.let { quantity },
        "unit_price" to unitPrice?.let { unitPrice }
    )
}
