package com.challenge.globalFlavorsHub.utils

import com.challenge.globalFlavorsHub.data.model.GlobalFlavorsHubResponse
import com.challenge.globalFlavorsHub.data.model.NetworkResource
import com.challenge.globalFlavorsHub.data.model.NetworkResource.Error
import com.challenge.globalFlavorsHub.data.model.NetworkResource.Loading
import com.challenge.globalFlavorsHub.data.model.NetworkResource.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response

/**
 * Flow builder that wraps the body of a [Response] from an Retrofit Interface into a
 * [NetworkResource] class.
 *
 * It reports [NetworkResource.Loading] before executing the retrofit request.
 */
fun <T> responseFlow(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> Response<T>,
): Flow<NetworkResource<T>> = flow {
    emit(Loading())
    emit(
        block()
            .let { response ->
                response
                    .takeIf { it.isSuccessful }
                    ?.let { Success(it.body()) }
                    ?: Error(ErrorHelper.handleHttpErrorMessage(response.code()))
            },
    )
}.catch {
    emit(it.asNetworkResource())
}.flowOn(dispatcher)

/**
 * Flow builder that wraps the data object from a [GlobalFlavorsHubResponse] object from a Retrofit [Response]
 * body into a [NetworkResource] class.
 *
 * It reports [NetworkResource.Loading] before executing the retrofit request.
 */
fun <R, T : GlobalFlavorsHubResponse<R>> globalFlavorsHubResponse(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> Response<T>,
): Flow<NetworkResource<R>> = responseFlow(dispatcher, block)
    .map { resource -> resource.map { globalFlavorsHubResponse -> globalFlavorsHubResponse?.data } }
    .catch { emit(it.asNetworkResource()) }
    .flowOn(dispatcher)

@OptIn(ExperimentalCoroutinesApi::class)
fun <R, T> Flow<NetworkResource<R>>.flatMapLatestResource(
    transform: suspend (R?) -> Flow<NetworkResource<T>>,
) = flatMapLatest { resource ->
    when (resource) {
        is Error -> flowOf(Error(message = resource.message.orEmpty()))
        is Loading -> flowOf(Loading())
        is Success -> transform(resource.data)
    }
}
