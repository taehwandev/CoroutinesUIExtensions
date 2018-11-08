package tech.thdev.support.base.coroutines.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
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