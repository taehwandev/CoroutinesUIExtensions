package tech.thdev.coroutines.provider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


sealed class DispatchersProviderSealed {
    open val main: CoroutineDispatcher by lazy { Dispatchers.Main }
    open val default: CoroutineDispatcher by lazy { Dispatchers.Default }
}

object DispatchersProvider : DispatchersProviderSealed()

/**
 * Unit Test를 위한 Dispatchers 정의
 * Default context provider.
 * Default default.
 */
object TestDispatchersProvider : DispatchersProviderSealed() {
    override val main: CoroutineDispatcher = Dispatchers.Default
    override val default: CoroutineDispatcher = Dispatchers.Default
}