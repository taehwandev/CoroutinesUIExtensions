package tech.thdev.coroutines.ui.operator

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.timeunit.TimeUnit
import kotlinx.coroutines.experimental.withTimeout

class CoroutinesThrottleFirst<T>(override val time: Long,
                              override val unit: TimeUnit = TimeUnit.MILLISECONDS) : CoroutinesTimeInterface<T> {

    private var startTime: Long = 0

    override suspend fun run(item: T): T {
        if (startTime == 0L) {
            startTime = System.currentTimeMillis()
        }
        return item
    }
}