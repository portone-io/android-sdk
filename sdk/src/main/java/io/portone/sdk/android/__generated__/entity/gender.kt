package io.portone.sdk.__generated__.entity

/**
 * 구매자 성별
 */
enum class Gender {
    /**
     * 남성
     */
    MALE,
    /**
     * 여성
     */
    FEMALE,
    /**
     * 기타
     */
    OTHER;

    fun toJson(): String = name
}
