package tech.thdev.coroutines

import kotlinx.coroutines.experimental.*
import org.junit.Test
import tech.thdev.coroutines.provider.TestDispatchersProvider

class TestTest {

    private suspend fun run() =
            CoroutineScope(TestDispatchersProvider.default).launch {
                for (i in 10 downTo 1) {
                    println("downTo $i")
                    delay(1)
                }
            }

    private suspend fun wait() {
        val ret = run()
        ret.join()
    }

    @Test
    fun test() = runBlocking {
        val job = CoroutineScope(Dispatchers.Unconfined).launch {
            delay(300L)
            println("World!")
        }
        println("Hello,")
        job.join()
    }


//    @Test
//    fun test() = runBlocking {
//        var count = 0
//        launch {
//            println("Task from runBlocking ${count++}")
//        }
//
//        coroutineScope { // Creates a new coroutine scope
//            launch {
//                delay(500L)
//                println("Task from nested launch ${count++}")
//            }
//
//            delay(100L)
//            println("Task from coroutine scope ${count++}") // This line will be printed before nested launch
//        }
//
//        println("Coroutine scope is over ${count++}") // This line is not printed until nested launch completes
//
////        CoroutineScope(Dispatchers.Unconfined).launch {
////            delay(300)
////            println("Coroutine")
////        }
////
////        GlobalScope.launch {
////            println("current threadd ${Thread.currentThread()}")
////        }
////
////        println("Subroutine")
////        delay(500)
//
//        loadData { item ->
//            // Coroutine scope 정의
//
//        }
//
//        GlobalScope.launch { // launch new coroutine in background and continue
//            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
//            println("World!") // print after delay
//        }
//        println("Hello,") // main thread continues while coroutine is delayed
//        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
//    }

    suspend fun CoroutineScope.loadData(body: suspend CoroutineScope.(item: String) -> Unit) {
        val item = ""
        delay(100L)
        body(item)
    }
}