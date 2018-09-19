package tech.thdev.coroutines.ui

import android.view.View
import kotlinx.coroutines.experimental.Job
import tech.thdev.coroutines.provider.CoroutineContextProvider
import tech.thdev.coroutines.provider.CoroutineContextSealed

open class CoroutinesOnClick<R>(private val view: View,
                                bgBody: suspend (item: View) -> R,
                                contextProvider: CoroutineContextSealed = CoroutineContextProvider,
                                job: Job? = null) : CoroutinesUIEventDefault<View, R>(bgBody, contextProvider, job) {


    override fun consumeEach(uiBody: (item: R) -> Unit): CoroutinesUIEventDefault<View, R> {
        super.consumeEach(uiBody)
        view.setOnClickListener { offer(it) }
        return this
    }
}

/**
 * Coroutines View click event create
 * @param bgBody background job.
 * @param contextProvider CoroutineContextProvider
 * @param job optional
 */
fun <R> viewClick(view: View, bgBody: suspend (item: View) -> R,
                  contextProvider: CoroutineContextSealed = CoroutineContextProvider,
                  job: Job? = null): CoroutinesUIEventDefault<View, R> =
        CoroutinesOnClick(view, bgBody, contextProvider, job)