package tech.thdev.coroutines.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import tech.thdev.support.delegate.BaseDelegate

/**
 * Only base coroutine scope.
 */
interface BaseCoroutineScope : BaseDelegate, CoroutineScope {

    val job: Job
}