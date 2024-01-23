package com.challenge.globalFlavorsHub.data.model

sealed class NetworkResource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : NetworkResource<T>(data)
    class Error<T>(message: String, data: T? = null) : NetworkResource<T>(data, message)
    class Loading<T>(data: T? = null) : NetworkResource<T>(data)

    fun <R> map(transformation: (T?) -> R?): NetworkResource<R> = when (this) {
        is Loading -> Loading()
        is Success -> Success(data = transformation(data))
        is Error -> Error(message = this.message.orEmpty(), data = transformation(data))
    }

    fun onSuccess(block: (T?) -> Unit): NetworkResource<T> = this.apply {
        when (this) {
            is Error -> Unit
            is Loading -> Unit
            is Success -> block(data)
        }
    }
}
