package tech.thdev.coroutines.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

/**
 * Only base coroutine scope.
 */
interface BaseCoroutineScope : CoroutineScope {

    val job: Job

    /**
     * Coroutine job cancel
     */
    fun releaseCoroutine()
}