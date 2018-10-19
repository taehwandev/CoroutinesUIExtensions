package tech.thdev.coroutines.base.ui.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import tech.thdev.coroutines.provider.DispatchersProvider
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Default CoroutineScope main thread.
 *
 * To change bg thread example.
 *
 * launch(DispatchersProvider.default) {}
 *
 * @property job Job
 * @property coroutineContext CoroutineContext : default main thread
 */
abstract class CoroutineScopeLifecycleObserver : LifecycleObserver, CoroutineScope {

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = DispatchersProvider.main + job

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        job.cancel()
    }
}