package tech.thdev.support.base.coroutines.viewmodel

import android.arch.lifecycle.ViewModel
import tech.thdev.coroutines.scope.BaseCoroutineScope
import tech.thdev.coroutines.scope.UICoroutineScope

/**
 * Default CoroutineScope main thread.
 *
 * To change main thread example.
 *
 * launch(DispatchersProvider.main) {
 * }
 *
 * @property job Job
 * @property coroutineContext CoroutineContext : default main thread
 */
abstract class CoroutineScopeViewModel @JvmOverloads constructor(
        scope: BaseCoroutineScope = UICoroutineScope()) : ViewModel(), BaseCoroutineScope by scope {

    override fun onCleared() {
        super.onCleared()

        release()
    }
}