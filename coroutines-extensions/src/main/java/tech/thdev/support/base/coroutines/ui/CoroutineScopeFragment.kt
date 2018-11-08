package tech.thdev.support.base.coroutines.ui

import android.support.v4.app.Fragment
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
abstract class CoroutineScopeFragment @JvmOverloads constructor(scope: BaseCoroutineScope = UICoroutineScope())
    : Fragment(), BaseCoroutineScope by scope {

    override fun onDestroy() {
        super.onDestroy()

        release()
    }
}