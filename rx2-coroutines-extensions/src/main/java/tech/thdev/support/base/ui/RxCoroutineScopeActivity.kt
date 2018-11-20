package tech.thdev.support.base.ui

import tech.thdev.rx.scope.BaseRxScope
import tech.thdev.rx.scope.RxScope
import tech.thdev.support.base.coroutines.ui.CoroutineScopeActivity

/**
 * Default CoroutineScope main thread and RxJava2 auto disposable
 */
abstract class RxCoroutineScopeActivity @JvmOverloads constructor(scope: BaseRxScope = RxScope())
    : CoroutineScopeActivity(), BaseRxScope by scope {

    override fun onDestroy() {
        super.onDestroy()

        releaseRx()
    }
}