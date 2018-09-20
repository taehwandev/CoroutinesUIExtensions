package tech.thdev.coroutines.ui

import kotlinx.coroutines.experimental.Job
import tech.thdev.coroutines.provider.CoroutineContextProvider
import tech.thdev.coroutines.provider.CoroutineContextSealed

class CoroutinesSendChannelUIEvent<E, R>(bgBody: suspend (item: E) -> R,
                                         contextProvider: CoroutineContextSealed = CoroutineContextProvider,
                                         job: Job? = null) : BaseCoroutinesSendChannelUIEvent<E, R>(bgBody, contextProvider, job)

/**
 * Coroutines UI Send event create
 * @param bgBody background job.
 * @param contextProvider CoroutineContextProvider
 * @param job optional
 */
fun <E, R> createUIEvent(bgBody: suspend (item: E) -> R,
                         contextProvider: CoroutineContextSealed = CoroutineContextProvider,
                         job: Job? = null): CoroutinesSendChannelUIEvent<E, R> =
        CoroutinesSendChannelUIEvent(bgBody, contextProvider, job)