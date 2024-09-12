package io.portone.sdk.android.type

enum class CashReceiptType {
    PERSONAL, // 소득공제용
    CORPORATE, // 지출증빙용
    ANONYMOUS; // 국세청번호 자동발급 케이스 대응
}
