package tech.thdev.coroutines.provider

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


sealed class DispatchersProviderSealed {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val default: CoroutineContext by lazy { Dispatchers.Default }
}

object DispatchersProvider : DispatchersProviderSealed()

/**
 * Default context provider.
 * Unconfined default.
 */
object TestDispatchersProvider : DispatchersProviderSealed() {
    override val main: CoroutineContext = Dispatchers.Default
    override val default: CoroutineContext = Dispatchers.Default
}