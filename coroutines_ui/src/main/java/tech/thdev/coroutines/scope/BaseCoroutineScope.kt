package tech.thdev.coroutines.scope

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import tech.thdev.support.delegate.BaseDelegate

interface BaseCoroutineScope : BaseDelegate, CoroutineScope {

    val job: Job
}