package tech.thdev.coroutines.ui

import android.view.View
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.map
import kotlinx.coroutines.experimental.timeunit.TimeUnit
import tech.thdev.coroutines.operator.throttleFirst
import tech.thdev.coroutines.operator.time.CoroutinesThrottleFirst
import tech.thdev.coroutines.provider.CoroutineContextSealed
import kotlin.coroutines.experimental.EmptyCoroutineContext

abstract class BaseCoroutinesSendChannelUIEvent<E, R>(private val bgBody: suspend (item: E) -> R,
                                                      private val contextProvider: CoroutineContextSealed,
                                                      private val job: Job? = null) {

    private var throttleFirst: CoroutinesThrottleFirst? = null

    private lateinit var clickActor: SendChannel<E>

    open fun setThrottleFirst(time: Long, unit: TimeUnit): BaseCoroutinesSendChannelUIEvent<E, R> {
        throttleFirst = CoroutinesThrottleFirst(time, unit)
        return this
    }

    open fun consumeEach(uiBody: (item: R) -> Unit): BaseCoroutinesSendChannelUIEvent<E, R> {
        clickActor = GlobalScope.actor(
                context = contextProvider.main + (job ?: EmptyCoroutineContext)) {
            if (throttleFirst != null) {
                this.channel.throttleFirst(context = contextProvider.default, throttleFirst = throttleFirst!!).map(context = contextProvider.default, transform = bgBody).consumeEach(uiBody)
            } else {
                this.channel.map(context = contextProvider.default, transform = bgBody).consumeEach(uiBody)
            }
        }
        return this
    }

    open fun offer(item: E) {
        if (::clickActor.isInitialized) {
            clickActor.offer(item)
        }
    }
}

fun <E : View, R> BaseCoroutinesSendChannelUIEvent<E, R>.throttleFirst(time: Long, unit: TimeUnit = TimeUnit.MILLISECONDS): BaseCoroutinesSendChannelUIEvent<E, R> =
        this.setThrottleFirst(time, unit)

infix fun <E : View, R> BaseCoroutinesSendChannelUIEvent<E, R>.update(uiBody: (item: R) -> Unit): BaseCoroutinesSendChannelUIEvent<E, R> =
        this.consumeEach(uiBody)