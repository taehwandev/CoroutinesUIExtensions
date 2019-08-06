package tech.thdev.coroutinesuiextensions

import kotlinx.coroutines.*
import org.junit.Test

class MainActivityTest {

    private val job = SupervisorJob()
    val global = GlobalScope
    private val coroutineScope = CoroutineScope(Dispatchers.Default + job)
    @Test
    fun testException() = runBlocking {
        with(coroutineScope) {
            println("Parent scope")
            val firstChildren = launch {
                println("First children is failing")
                throw Exception("First children is exception")
            }
            val secondChildren = launch {
                println("Second children is success")
                delay(500)
                println("Second children is delay 500ms")
            }
            firstChildren.join()
            secondChildren.join()
        }
    }
}