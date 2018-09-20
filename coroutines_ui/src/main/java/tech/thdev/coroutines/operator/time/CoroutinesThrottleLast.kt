package tech.thdev.coroutines.operator.time

import kotlinx.coroutines.experimental.timeunit.TimeUnit

class CoroutinesThrottleLast(intervalDuration: Long,
                             unit: TimeUnit = TimeUnit.MILLISECONDS) {
    private val timeInMilliseconds = unit.toMillis(intervalDuration)
    private var last: Long = 0

    fun hasNext(): Boolean {
        val nowTime = System.currentTimeMillis()
        if (last == 0L || nowTime - last >= timeInMilliseconds) {
            last = nowTime
            return true
        }
        return false
    }
}