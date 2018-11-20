package tech.thdev.support.base.lifecycle

import tech.thdev.rx2.scope.BaseRxScope
import tech.thdev.rx2.scope.RxScope
import tech.thdev.support.base.coroutines.lifecycle.CoroutineScopeLifecycleObserver

/**
 * Default CoroutineScope main thread and RxJava2 auto disposable
 */
abstract class RxCoroutineScopeLifecycleObserver @JvmOverloads constructor(
        scope: BaseRxScope = RxScope()) : CoroutineScopeLifecycleObserver(), BaseRxScope by scope {

    override fun onDestroy() {
        super.onDestroy()
        releaseRx()
    }
}