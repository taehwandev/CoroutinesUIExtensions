package tech.thdev.coroutines.operator.time

import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class CoroutinesThrottleFirstTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testOneHundred() = runBlocking {
        val throttleFirst = CoroutinesThrottleFirst(100, TimeUnit.MILLISECONDS)

        val responseHasNext = BooleanArray(2)
        responseHasNext[0] = true
        responseHasNext[1] = false
        // assert
        (1..2).forEach { index ->
            assert(responseHasNext[index - 1] == throttleFirst.hasNext())
        }
    }
}