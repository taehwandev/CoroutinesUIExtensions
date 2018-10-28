package tech.thdev.support.base.coroutines.ui

import androidx.appcompat.app.AppCompatActivity
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
abstract class CoroutineScopeActivity @JvmOverloads constructor(scope: BaseCoroutineScope = UICoroutineScope())
    : AppCompatActivity(), BaseCoroutineScope by scope {

    override fun onDestroy() {
        super.onDestroy()

        release()
    }
}