package tech.thdev.support.base.viewmodel

import tech.thdev.rx.scope.BaseRxScope
import tech.thdev.rx.scope.RxScope
import tech.thdev.support.base.coroutines.viewmodel.CoroutineScopeViewModel

/**
 * Default CoroutineScope default thread and RxJava2 auto disposable
 */
abstract class RxCoroutineScopeViewModel @JvmOverloads constructor(
        scope: BaseRxScope = RxScope()) : CoroutineScopeViewModel(), BaseRxScope by scope {

    override fun onCleared() {
        super.onCleared()

        releaseRx()
    }
}