package tech.thdev.coroutines.scope

import android.util.Log
import kotlinx.coroutines.Job
import tech.thdev.coroutines.BuildConfig.DEBUG
import tech.thdev.coroutines.provider.DispatchersProvider
import kotlin.coroutines.CoroutineContext

class UICoroutineScope(private val dispatchers: CoroutineContext = DispatchersProvider.main) : BaseCoroutineScope {

    override val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatchers + job

    override fun releaseCoroutine() {
        if (DEBUG) {
            Log.d("UICoroutineScope", "onRelease coroutine")
        }
        job.cancel()
    }
}