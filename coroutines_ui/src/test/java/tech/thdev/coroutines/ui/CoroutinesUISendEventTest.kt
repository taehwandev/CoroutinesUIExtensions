package tech.thdev.coroutines.ui

import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import tech.thdev.coroutines.provider.CoroutineContextProviderDefault
import java.util.concurrent.TimeUnit

class CoroutinesUISendEventTest {

    @Test
    fun throttleFirst() = runBlocking {
        var count = 0
        val onClick = CoroutinesSendChannelUIEvent<Int, Int>(
                bgBody = {
                    it
                },
                contextProvider = CoroutineContextProviderDefault)
        onClick.setThrottleFirst(500, TimeUnit.MILLISECONDS)
        onClick.consumeEach {
            println("getCount $it")
            assert(it == 0)
        }

        (1..5).forEach { _ ->
            onClick.offer(count++)

            Thread.sleep(100)
        }
    }

    @Test
    fun throttleLast() = runBlocking {
        var count = 0
        val onClick = CoroutinesSendChannelUIEvent<Int, Int>(
                bgBody = {
                    println("bgBody $it")
                    it
                },
                contextProvider = CoroutineContextProviderDefault)
        onClick.throttleLast(500, TimeUnit.MILLISECONDS)
        onClick.consumeEach {
            println("getCount $it")
        }

        (1..5).forEach {  _ ->
            onClick.offer(++count)
            println("++count $count")
            Thread.sleep(100)
        }
        Thread.sleep(5000)
    }
}