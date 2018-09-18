package tech.thdev.coroutines.ui.operator

import kotlinx.coroutines.experimental.timeunit.TimeUnit

interface CoroutinesTimeInterface<T> {

    val time: Long
    val unit: TimeUnit

    suspend fun run(item: T ): T
}