package tech.thdev.coroutines.operator.time

import kotlinx.coroutines.experimental.timeunit.TimeUnit

class CoroutinesThrottleFirst(time: Long,
                              unit: TimeUnit = TimeUnit.MILLISECONDS) : CoroutinesTimeInterface {

    private val timeInMilliseconds = unit.toMillis(time)
    private var last: Long = 0

    override suspend fun hasNext(): Boolean {
        val nowTime = System.currentTimeMillis()
        if (last == 0L || nowTime - last >= timeInMilliseconds) {
            last = nowTime
            return true
        }
        return false
    }
}