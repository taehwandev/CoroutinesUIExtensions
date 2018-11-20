package tech.thdev.support.base.viewmodel

import android.app.Application
import tech.thdev.rx.scope.BaseRxScope
import tech.thdev.rx.scope.RxScope
import tech.thdev.support.base.coroutines.viewmodel.CoroutineScopeAndroidViewModel

/**
 * Default CoroutineScope default thread and RxJava2 auto disposable
 */
abstract class RxCoroutineScopeAndroidViewModel @JvmOverloads constructor(
        application: Application,
        scope: BaseRxScope = RxScope()) : CoroutineScopeAndroidViewModel(application), BaseRxScope by scope {

    override fun onCleared() {
        super.onCleared()

        releaseRx()
    }
}