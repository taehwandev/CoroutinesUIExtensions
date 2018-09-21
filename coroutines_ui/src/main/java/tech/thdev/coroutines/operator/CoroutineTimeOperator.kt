package tech.thdev.coroutines.operator

import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consumes
import kotlinx.coroutines.experimental.channels.produce
import tech.thdev.coroutines.operator.time.CoroutinesThrottleFirst
import tech.thdev.coroutines.operator.time.CoroutinesThrottleLast
import kotlin.coroutines.experimental.CoroutineContext

/**
 *
 * @param E
 * @param context
 * @param throttleFirst
 */
fun <E> ReceiveChannel<E>.throttleFirst(context: CoroutineContext = Dispatchers.Unconfined, throttleFirst: CoroutinesThrottleFirst): ReceiveChannel<E> =
        GlobalScope.produce(context, onCompletion = consumes()) {
            for (e in this@throttleFirst) {
                if (throttleFirst.hasNext()) send(e)
            }
        }

fun <E> ReceiveChannel<E>.throttleLast(context: CoroutineContext = Dispatchers.Unconfined, throttleLast: CoroutinesThrottleLast): ReceiveChannel<E> =
        GlobalScope.produce(context, onCompletion = consumes()) {
            for (e in this@throttleLast) {
                throttleLast.next(e, this)
            }
        }