package tech.thdev.support.base.ui

import tech.thdev.rx2.scope.BaseRxScope
import tech.thdev.rx2.scope.RxScope
import tech.thdev.support.base.coroutines.ui.CoroutineScopeFragment

/**
 * Default CoroutineScope main thread and RxJava2 auto disposable
 */
abstract class RxCoroutineScopeFragment @JvmOverloads constructor(scope: BaseRxScope = RxScope())
    : CoroutineScopeFragment(), BaseRxScope by scope {

    override fun onDestroy() {
        super.onDestroy()

        releaseRx()
    }
}