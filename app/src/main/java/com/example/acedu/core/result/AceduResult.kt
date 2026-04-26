package com.example.acedu.core.result

sealed class AceduResult<out T> {
    data class Success<T>(val data: T) : AceduResult<T>()
    data class Error(
        val message: String,
        val exception: Throwable? = null,
        val httpCode: Int? = null
    ) : AceduResult<Nothing>()

    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error

    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Error -> null
    }

    fun exceptionOrNull(): Throwable? = when (this) {
        is Success -> null
        is Error -> exception
    }

    inline fun onSuccess(action: (T) -> Unit): AceduResult<T> {
        if (this is Success) {
            action(data)
        }
        return this
    }

    inline fun onError(action: (String, Throwable?) -> Unit): AceduResult<T> {
        if (this is Error) {
            action(message, exception)
        }
        return this
    }
}
