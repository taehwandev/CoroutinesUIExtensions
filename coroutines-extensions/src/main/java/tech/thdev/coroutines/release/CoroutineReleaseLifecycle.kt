package tech.thdev.coroutines.release

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import kotlinx.coroutines.Job
import tech.thdev.coroutines.BuildConfig

class CoroutineReleaseLifecycle(val job: Job) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onCancelCoroutine() {
        if (BuildConfig.DEBUG) {
            Log.d("CoroutineLifecycle", "Release coroutine Job")
        }
        job.cancel()
    }
}