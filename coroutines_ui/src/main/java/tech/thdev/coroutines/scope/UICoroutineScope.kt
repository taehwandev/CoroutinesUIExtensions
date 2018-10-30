package tech.thdev.coroutines.scope

import android.util.Log
import kotlinx.coroutines.experimental.Job
import tech.thdev.coroutines.provider.DispatchersProvider
import kotlin.coroutines.experimental.CoroutineContext

class UICoroutineScope : BaseCoroutineScope {

    override val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = DispatchersProvider.main + job

    override fun release() {
        Log.d("UICoroutineScope", "base release!!!!!!!! cancel")
        job.cancel()
    }
}