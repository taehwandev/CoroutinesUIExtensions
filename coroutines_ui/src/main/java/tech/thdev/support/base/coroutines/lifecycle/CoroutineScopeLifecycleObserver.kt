package tech.thdev.support.base.coroutines.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import tech.thdev.coroutines.scope.BaseCoroutineScope
import tech.thdev.coroutines.scope.UICoroutineScope

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
abstract class CoroutineScopeLifecycleObserver @JvmOverloads constructor(
        scope: BaseCoroutineScope = UICoroutineScope()) : LifecycleObserver, BaseCoroutineScope by scope {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        release()
    }
}