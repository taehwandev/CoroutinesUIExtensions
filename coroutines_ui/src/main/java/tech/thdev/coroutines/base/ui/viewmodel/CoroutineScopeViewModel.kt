package tech.thdev.coroutines.base.ui.viewmodel

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import tech.thdev.coroutines.provider.DispatchersProvider
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Default CoroutineScope default thread.
 *
 * To change main thread example.
 *
 * launch(DispatchersProvider.main) {
 * }
 *
 * @property job Job
 * @property coroutineContext CoroutineContext : default main thread
 */
abstract class CoroutineScopeViewModel : ViewModel(), CoroutineScope {

    protected val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = DispatchersProvider.default + job

    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }
}