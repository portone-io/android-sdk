package io.portone.sdk.android

interface Callback <S, F>  {
    fun onSuccess(response: S)
    fun onFail(response: F)
}