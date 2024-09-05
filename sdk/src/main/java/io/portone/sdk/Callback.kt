package io.portone.sdk

interface Callback <S, F>  {
    fun onSuccess(response: S)
    fun onFail(response: F)
}