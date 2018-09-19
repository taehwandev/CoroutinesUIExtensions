package tech.thdev.coroutines.ui

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.timeunit.TimeUnit
import tech.thdev.coroutines.provider.CoroutineContextProvider
import tech.thdev.coroutines.provider.CoroutineContextSealed

class CoroutinesUISendEvent<E, R>(bgBody: suspend (item: E) -> R,
                                  contextProvider: CoroutineContextSealed = CoroutineContextProvider,
                                  job: Job? = null) : CoroutinesUIEventDefault<E, R>(bgBody, contextProvider, job)

/**
 * Coroutines UI Send event create
 * @param bgBody background job.
 * @param contextProvider CoroutineContextProvider
 * @param job optional
 */
fun <E, R> createUIEvent(bgBody: suspend (item: E) -> R,
                         contextProvider: CoroutineContextSealed = CoroutineContextProvider,
                         job: Job? = null): CoroutinesUISendEvent<E, R> =
        CoroutinesUISendEvent(bgBody, contextProvider, job)

fun <E, R> CoroutinesUISendEvent<E, R>.throttleFirst(time: Long, unit: TimeUnit = TimeUnit.MILLISECONDS): CoroutinesUISendEvent<E, R> =
        this.setThrottleFirst(time, unit) as CoroutinesUISendEvent<E, R>

infix fun <E, R> CoroutinesUISendEvent<E, R>.update(uiBody: (item: R) -> Unit): CoroutinesUISendEvent<E, R> =
        this.consumeEach(uiBody) as CoroutinesUISendEvent<E, R>