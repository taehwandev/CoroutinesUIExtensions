package tech.thdev.coroutines.base.ui

import android.support.v4.app.Fragment
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import tech.thdev.coroutines.provider.DispatchersProvider
import kotlin.coroutines.experimental.CoroutineContext

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
abstract class CoroutineScopeAppCompatFragment : Fragment(), CoroutineScope {

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = DispatchersProvider.main + job

    override fun onDestroy() {
        super.onDestroy()

        job.cancel()
    }
}