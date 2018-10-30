package tech.thdev.coroutines.ui

import android.view.View
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.map
import tech.thdev.coroutines.provider.DispatchersProvider
import tech.thdev.coroutines.provider.DispatchersProviderSealed
import kotlin.coroutines.experimental.EmptyCoroutineContext

class CoroutinesSendChannelOnClickEvent<E>(
        private val view: View,
        private val bgBody: suspend (item: View) -> E,
        private val dispatcherProvider: DispatchersProviderSealed = DispatchersProvider,
        private val job: Job? = null) {

    fun consumeEach(uiBody: (item: E) -> Unit): CoroutinesSendChannelOnClickEvent<E> {
        val clickActor = CoroutineScope(dispatcherProvider.main + (job
                ?: EmptyCoroutineContext)).actor<View> {
            this.channel.map(context = dispatcherProvider.default, transform = bgBody).consumeEach(uiBody)
        }
        view.setOnClickListener { clickActor.offer(it) }
        return this
    }
}

fun <E> View.onClick(dispatcherProvider: DispatchersProviderSealed = DispatchersProvider,
                     job: Job? = null, bgBody: suspend (item: View) -> E): CoroutinesSendChannelOnClickEvent<E> =
        CoroutinesSendChannelOnClickEvent(this, bgBody, dispatcherProvider, job)

infix fun <E> CoroutinesSendChannelOnClickEvent<E>.consume(uiBody: (item: E) -> Unit) {
    this.consumeEach(uiBody)
}