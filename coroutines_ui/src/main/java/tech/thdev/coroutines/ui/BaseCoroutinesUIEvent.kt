package tech.thdev.coroutines.ui

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

abstract class CoroutinesUIEventDefault<E, R>(private val bgBody: suspend (item: E) -> R,
                                              private val contextProvider: CoroutineContextSealed,
                                              private val job: Job? = null) {

    private var throttleFirst: CoroutinesThrottleFirst? = null

    private lateinit var clickActor: SendChannel<E>

    open fun setThrottleFirst(time: Long, unit: TimeUnit): CoroutinesUIEventDefault<E, R> {
        throttleFirst = CoroutinesThrottleFirst(time, unit)
        return this
    }

    open fun consumeEach(uiBody: (item: R) -> Unit): CoroutinesUIEventDefault<E, R> {
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