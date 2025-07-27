package io.portone.sdk.__generated__.entity

import android.os.Parcelable
import io.portone.sdk.__generated__.entity.OfferPeriodRange
import kotlinx.parcelize.Parcelize

/**
 * **서비스 제공 기간**
 * 
 * range와 interval 중 하나를 입력해주세요.
 * 
 * - range: 제공 기간 범위
 * - interval: 제공 기간 주기
 * 
 * 예1) 2023년 1월 1일 00시 00분 00초(KST) ~
 * 
 * ```js
 * range: {
 * from: '2023-01-01T00:00:00+09:00'
 * }
 * ```
 * 
 * 예2) ~ 2023년 1월 1일 00시 00분 00초(KST)
 * 
 * ```js
 * range: {
 * to: '2023-01-01T00:00:00+09:00'
 * }
 * ```
 * 
 * 예3) 2023년 1월 1일 00시 00분 00초(KST) ~ 2023년 12월 31일 23시 59분 59초(KST)
 * 
 * ```js
 * range: {
 * from: '2023-12-31T23:59:59+09:00'
 * to: '2023-01-01T00:00:00+09:00'
 * }
 * ```
 * 
 * 예4) 30일 주기
 * `interval: '30d'`
 * 
 * 예5) 6개월 주기
 * `interval: '6m'`
 * 
 * 예6) 1년 주기
 * `interval: '1y'`
 */
@Parcelize
sealed interface OfferPeriod : Parcelable {
    /**
     * **기간 범위**
     */
    @Parcelize
    data class Range(val value: OfferPeriodRange) : OfferPeriod
    /**
     * **제공 주기**
     * 
     * 제공 주기 (`${number}d | ${number}m | ${number}y` 형태로 입력할 수 있습니다.)
     */
    @Parcelize
    data class Interval(val value: String) : OfferPeriod

    fun toJson(): Map<String, Any> = when (this) {
        is Range -> mapOf("range" to value.toJson())
        is Interval -> mapOf("interval" to value)
    }
}
