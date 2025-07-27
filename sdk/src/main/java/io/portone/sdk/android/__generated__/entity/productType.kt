package io.portone.sdk.__generated__.entity

/**
 * **상품 유형**
 */
enum class ProductType {
    /**
     * 실물
     */
    REAL,
    /**
     * 디지털
     */
    DIGITAL;

    fun toJson(): String = name
}
