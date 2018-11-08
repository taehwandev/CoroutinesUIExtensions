package tech.thdev.coroutines.scope

import android.util.Log
import kotlinx.coroutines.Job
import tech.thdev.coroutines.BuildConfig.DEBUG
import tech.thdev.coroutines.provider.DispatchersProvider
import kotlin.coroutines.CoroutineContext

class UICoroutineScope : BaseCoroutineScope {

    override val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = DispatchersProvider.main + job

    override fun release() {
        if (DEBUG) {
            Log.d("UICoroutineScope", "Release coroutine ${job.children.count()}")
        }
        job.cancel()
    }
}