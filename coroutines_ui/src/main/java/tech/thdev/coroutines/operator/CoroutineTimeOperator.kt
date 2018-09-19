package tech.thdev.coroutines.operator

import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consumes
import kotlinx.coroutines.experimental.channels.produce
import tech.thdev.coroutines.operator.time.CoroutinesTimeInterface
import kotlin.coroutines.experimental.CoroutineContext

/**
 *
 * @param E
 * @param context
 * @param throttleFirst
 */
fun <E> ReceiveChannel<E>.throttleFirst(context: CoroutineContext = Unconfined, throttleFirst: CoroutinesTimeInterface): ReceiveChannel<E> =
        GlobalScope.produce(context, onCompletion = consumes()) {
            for (e in this@throttleFirst) {
                if (throttleFirst.hasNext()) send(e)
            }
        }
