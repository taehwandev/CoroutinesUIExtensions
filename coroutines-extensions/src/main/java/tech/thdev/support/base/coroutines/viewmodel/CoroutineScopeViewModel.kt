package tech.thdev.support.base.coroutines.viewmodel

import android.arch.lifecycle.ViewModel
import tech.thdev.coroutines.provider.DispatchersProvider
import tech.thdev.coroutines.scope.BaseCoroutineScope
import tech.thdev.coroutines.scope.UICoroutineScope

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
abstract class CoroutineScopeViewModel @JvmOverloads constructor(
        scope: BaseCoroutineScope = UICoroutineScope(DispatchersProvider.default)) : ViewModel(), BaseCoroutineScope by scope {

    override fun onCleared() {
        super.onCleared()

        release()
    }
}