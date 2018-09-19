package tech.thdev.coroutines.ui

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.timeunit.TimeUnit
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

fun <E, R> CoroutinesSendChannelUIEvent<E, R>.throttleFirst(time: Long, unit: TimeUnit = TimeUnit.MILLISECONDS): CoroutinesSendChannelUIEvent<E, R> =
        this.setThrottleFirst(time, unit) as CoroutinesSendChannelUIEvent<E, R>

infix fun <E, R> CoroutinesSendChannelUIEvent<E, R>.update(uiBody: (item: R) -> Unit): CoroutinesSendChannelUIEvent<E, R> =
        this.consumeEach(uiBody) as CoroutinesSendChannelUIEvent<E, R>