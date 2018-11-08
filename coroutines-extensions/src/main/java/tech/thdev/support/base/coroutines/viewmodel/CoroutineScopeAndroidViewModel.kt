package tech.thdev.support.base.coroutines.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
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
abstract class CoroutineScopeAndroidViewModel @JvmOverloads constructor(
        application: Application,
        scope: BaseCoroutineScope = UICoroutineScope()) : AndroidViewModel(application), BaseCoroutineScope by scope {

    override fun onCleared() {
        super.onCleared()

        release()
    }
}