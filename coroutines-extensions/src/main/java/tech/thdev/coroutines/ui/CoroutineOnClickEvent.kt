package tech.thdev.coroutines.ui

import android.view.View
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import tech.thdev.coroutines.provider.DispatchersProvider
import tech.thdev.coroutines.provider.DispatchersProviderSealed

/**
 * View on click coroutine example
 */
class CoroutineOnClickEvent<E>(
        private val view: View,
        bgBody: suspend (item: View) -> E,
        dispatcherProvider: DispatchersProviderSealed = DispatchersProvider,
        job: Job? = null) : CoroutineUIEvent<View, E>(bgBody, dispatcherProvider, job) {

    /**
     * background thread
     * @param uiBody
     */
    @ObsoleteCoroutinesApi
    override fun initUiSender(uiBody: (item: E) -> Unit): CoroutineUIEvent<View, E> {
        super.initUiSender(uiBody)

        view.setOnClickListener {
            offer(it)
        }
        return this
    }
}

/**
 * Button onClick and background body.
 * @param dispatcherProvider : default Default thread and main thread
 * @param job : default null, add job
 * @param bgBody : background thread
 */
fun <E> View.onClick(dispatcherProvider: DispatchersProviderSealed = DispatchersProvider,
                     job: Job? = null, bgBody: suspend (item: View) -> E): CoroutineOnClickEvent<E> =
        CoroutineOnClickEvent(this, bgBody, dispatcherProvider, job)

/**
 * run on ui thread.
 * @param uiBody run ui thread body
 */
@ObsoleteCoroutinesApi
infix fun <E> CoroutineOnClickEvent<E>.runUi(uiBody: (item: E) -> Unit): CoroutineUIEvent<View, E> =
        this.initUiSender(uiBody)