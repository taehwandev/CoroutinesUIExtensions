package tech.thdev.coroutines.ui

import android.view.View
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.timeunit.TimeUnit
import tech.thdev.coroutines.provider.CoroutineContextProvider
import tech.thdev.coroutines.provider.CoroutineContextSealed

@Suppress("UNCHECKED_CAST")
open class CoroutinesOnClick<E : View, R>(private val view: E,
                                          bgBody: suspend (item: E) -> R,
                                          contextProvider: CoroutineContextSealed = CoroutineContextProvider,
                                          job: Job? = null) : CoroutinesUIEventDefault<E, R>(bgBody, contextProvider, job) {


    override fun consumeEach(uiBody: (item: R) -> Unit): CoroutinesUIEventDefault<E, R> {
        super.consumeEach(uiBody)
        view.setOnClickListener { offer(it as E) }
        return this
    }
}

/**
 * Coroutines View click event create
 * @param bgBody background job.
 * @param contextProvider CoroutineContextProvider
 * @param job optional
 */
fun <E : View, R> viewClick(view: E,
                            contextProvider: CoroutineContextSealed = CoroutineContextProvider,
                            job: Job? = null,
                            bgBody: suspend (item: View) -> R): CoroutinesOnClick<E, R> =
        CoroutinesOnClick(view, bgBody, contextProvider, job)

fun <E : View, R> CoroutinesOnClick<E, R>.throttleFirst(time: Long, unit: TimeUnit = TimeUnit.MILLISECONDS): CoroutinesOnClick<E, R> =
        this.setThrottleFirst(time, unit) as CoroutinesOnClick<E, R>

infix fun <E : View, R> CoroutinesOnClick<E, R>.update(uiBody: (item: R) -> Unit): CoroutinesOnClick<E, R> =
        this.consumeEach(uiBody) as CoroutinesOnClick<E, R>