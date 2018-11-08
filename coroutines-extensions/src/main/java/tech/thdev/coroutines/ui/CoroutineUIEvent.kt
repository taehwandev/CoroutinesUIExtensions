package tech.thdev.coroutines.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.map
import tech.thdev.coroutines.provider.DispatchersProvider
import tech.thdev.coroutines.provider.DispatchersProviderSealed
import tech.thdev.coroutines.scope.BaseCoroutineScope

open class CoroutineUIEvent<E, R>(private val bgBody: suspend (item: E) -> R,
                                  private val dispatcherProvider: DispatchersProviderSealed = DispatchersProvider,
                                  private val job: Job) {

    private lateinit var uiSender: SendChannel<E>

    /**
     * background thread
     * @param uiBody
     */
    @ObsoleteCoroutinesApi
    open fun initUiSender(uiBody: (item: R) -> Unit): CoroutineUIEvent<E, R> {
        uiSender = CoroutineScope(dispatcherProvider.main + job).actor {
            this.channel.map(context = dispatcherProvider.default, transform = bgBody).consumeEach(uiBody)
        }
        return this
    }

    /**
     * Offer SendChannel event.
     * @param element
     */
    open fun offer(element: E) {
        if (::uiSender.isInitialized) {
            uiSender.offer(element)
        }
    }
}

/**
 * Button onClick and background body.
 * @param job : default null, add job
 * @param dispatcherProvider : default Default thread and main thread
 * @param bgBody : background thread
 */
fun <E, R> BaseCoroutineScope.createUiEvent(
        dispatcherProvider: DispatchersProviderSealed = DispatchersProvider,
        bgBody: suspend (item: E) -> R): CoroutineUIEvent<E, R> =
        CoroutineUIEvent(bgBody, dispatcherProvider, job)

/**
 * run on ui thread.
 * @param uiBody run ui thread body
 */
@ObsoleteCoroutinesApi
infix fun <E, R> CoroutineUIEvent<E, R>.runUi(uiBody: (item: R) -> Unit) {
    this.initUiSender(uiBody)
}