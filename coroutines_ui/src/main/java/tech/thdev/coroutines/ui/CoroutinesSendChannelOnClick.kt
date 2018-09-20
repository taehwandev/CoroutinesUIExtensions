package tech.thdev.coroutines.ui

import android.view.View
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.timeunit.TimeUnit
import tech.thdev.coroutines.provider.CoroutineContextProvider
import tech.thdev.coroutines.provider.CoroutineContextSealed

@Suppress("UNCHECKED_CAST")
open class CoroutinesSendChannelOnClick<E : View, R>(private val view: E,
                                                     bgBody: suspend (item: E) -> R,
                                                     contextProvider: CoroutineContextSealed = CoroutineContextProvider,
                                                     job: Job? = null) : BaseCoroutinesSendChannelUIEvent<E, R>(bgBody, contextProvider, job) {


    override fun consumeEach(uiBody: (item: R) -> Unit): BaseCoroutinesSendChannelUIEvent<E, R> {
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
                            bgBody: suspend (item: View) -> R): CoroutinesSendChannelOnClick<E, R> =
        CoroutinesSendChannelOnClick(view, bgBody, contextProvider, job)