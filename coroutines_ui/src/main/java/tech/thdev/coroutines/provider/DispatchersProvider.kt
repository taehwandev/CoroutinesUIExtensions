package tech.thdev.coroutines.provider

import kotlinx.coroutines.experimental.Dispatchers
import kotlin.coroutines.experimental.CoroutineContext

sealed class DispatchersProviderSealed {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val default: CoroutineContext by lazy { Dispatchers.Default }
}

object DispatchersProvider : DispatchersProviderSealed()

/**
 * Unit Test를 위한 Dispatchers 정의
 * Default context provider.
 * Unconfined default.
 */
object TestDispatchersProvider : DispatchersProviderSealed() {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val default: CoroutineContext = Dispatchers.Unconfined
}