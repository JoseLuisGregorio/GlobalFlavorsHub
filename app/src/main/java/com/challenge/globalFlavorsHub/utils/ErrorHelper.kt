package com.challenge.globalFlavorsHub.utils

import com.challenge.globalFlavorsHub.data.model.NetworkResource.Error
import com.challenge.globalFlavorsHub.ui.Constants
import com.challenge.globalFlavorsHub.utils.HttpError.ERROR_400
import com.challenge.globalFlavorsHub.utils.HttpError.ERROR_404
import com.challenge.globalFlavorsHub.utils.HttpError.ERROR_409
import com.challenge.globalFlavorsHub.utils.HttpError.ERROR_500
import retrofit2.HttpException
import java.io.IOException

/**
 * Generic Helper to handle error response
 */
object ErrorHelper {
    fun handleThrowable(throwable: Throwable?): String = when (throwable) {
        is IOException -> handleIOException(throwable)
        is HttpException -> handleHttpErrorMessage(throwable.code())
        else -> handleException(throwable)
    }

    fun handleExceptionMessage(exception: Exception?): String {
        return when (exception) {
            is IOException -> handleIOException(exception)
            else -> handleException(exception)
        }
    }

    fun handleHttpErrorMessage(errorCode: Int): String {
        return when (errorCode) {
            ERROR_400.codeValue -> Constants.ERROR_400
            ERROR_404.codeValue -> Constants.ERROR_404
            ERROR_409.codeValue -> Constants.ERROR_400
            ERROR_500.codeValue -> Constants.ERROR_500
            else -> Constants.GENERAL_ERROR_MESSAGE
        }
    }

    private fun handleIOException(ioException: IOException): String {
        return Constants.LOST_INTERNET_CONNECTION
    }

    private fun handleException(throwable: Throwable?): String =
        throwable?.message ?: Constants.GENERAL_ERROR_MESSAGE
}

fun <T> Throwable.asNetworkResource() = Error<T>(ErrorHelper.handleThrowable(this))

enum class HttpError(val codeValue: Int) {
    ERROR_400(400),
    ERROR_404(404),
    ERROR_409(409),
    ERROR_500(500),
}
