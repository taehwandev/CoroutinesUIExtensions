package tech.thdev.coroutines.operator.time

import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.channels.ProducerScope
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.timeunit.TimeUnit

/**
 * 현재 시간 + 다음 실행 시간 = 다음번 send 시점
 * 마지막 실행 시점 = 현재 시간
 *
 *
 * ex
 * 0초에 눌름, 중간에 다른게 없다면 1초에 실행해야함
 *
 */
class CoroutinesThrottleLast(delayTime: Long,
                             unit: TimeUnit = TimeUnit.MILLISECONDS) {
    private val timeDelayNanos = unit.toNanos(delayTime)
    private var lastTimeDelayNanos = now(TimeUnit.NANOSECONDS)
    private var startTimeDelayNanos = lastTimeDelayNanos + timeDelayNanos
    private var count = 0

    fun <E> next(e: E, producerScope: ProducerScope<E>) {
        var nextTick = 0L
        val nowNanoseconds = now(TimeUnit.NANOSECONDS)

        if (nowNanoseconds < lastTimeDelayNanos ||
                nowNanoseconds >= lastTimeDelayNanos + timeDelayNanos) {
            nextTick = nowNanoseconds + timeDelayNanos
            startTimeDelayNanos = nextTick - (timeDelayNanos * (++count))
        } else {
            nextTick = startTimeDelayNanos + (++count * timeDelayNanos)
        }
        lastTimeDelayNanos = nowNanoseconds

        val delay = nextTick - nowNanoseconds
        println("delay $delay")

        GlobalScope.launch {
            val unit = TimeUnit.MILLISECONDS
            println("nowTime ${unit.toNanos(System.currentTimeMillis())} timeDelayNanos $timeDelayNanos")
            delay(delay, TimeUnit.NANOSECONDS)
            println("nowTime ${unit.toNanos(System.currentTimeMillis())}")
            producerScope.send(e)
        }
    }

    private fun now(unit: TimeUnit): Long =
            unit.toNanos(System.currentTimeMillis())
}