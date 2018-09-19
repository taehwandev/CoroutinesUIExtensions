package tech.thdev.coroutines.provider

import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

sealed class CoroutineContextSealed {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val default: CoroutineContext by lazy { Dispatchers.Default }
}

object CoroutineContextProvider : CoroutineContextSealed()

/**
 * Default context provider.
 * Unconfined default.
 */
object CoroutineContextProviderDefault : CoroutineContextSealed() {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val default: CoroutineContext = Dispatchers.Unconfined
}