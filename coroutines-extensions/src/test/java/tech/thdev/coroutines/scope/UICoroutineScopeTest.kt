package tech.thdev.coroutines.scope

import android.accounts.NetworkErrorException
import android.os.NetworkOnMainThreadException
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import org.junit.Test
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

class UICoroutineScopeTest {

    val errorHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        // log to Crashlytics, logcat, etc.; can be dependency injected
        println(exception)
        when (exception) {
            is NetworkErrorException -> {
                // Exception
            }
            is NetworkOnMainThreadException -> {
                // Exception
            }
            is Exception -> {

            }
        }
    }

    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Default)
    @Test
    fun testException() = runBlocking {
        supervisorScope {

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

    suspend fun doLogin(userName: String, password: String): Boolean =
            suspendCancellableCoroutine { continuation ->
                continuation.resume(userName == "tae" && password == "password")
                continuation.cancel()
            }



    @Test
    fun testCancellable() = runBlocking {
        coroutineScope.run {
            doLogin("tae", "password")

        }
        delay(1000)
    }


//    private val job = SupervisorJob()
//    private val coroutineScope = CoroutineScope(job + Dispatchers.Default + errorHandler)
//    @Test
//    fun testException() = runBlocking {
//        val job = Job()
//        CoroutineScope(Dispatchers.Default + job).launch(Dispatchers.IO + job)


//        with(coroutineScope) {
//            println("Parent job")
//            val childJob = launch(errorHandler) {
//                println("First child is failing")
//                throw Exception("First child is exception")
//            }
//            val secondJob = launch {
//                println("Second child is success")
//                delay(500)
//                println("Second child is delay 500ms")
//            }
//            childJob.join()
//            secondJob.join()
//        }
//    }

    @Test
    fun testCoroutines() = runBlocking {
        val job = Job()
        CoroutineScope(Dispatchers.Default + job).launch {
            CoroutineScope(Dispatchers.Default).launch {
                println("New CoroutineScope for start")
                for (index in 0..20) {
                    if (isActive) {
                        println("New CoroutineScope index $index")
                        delay(1)
                    } else {
                        break
                    }
                }
                println("New CoroutineScope  for end")
            }
            val defaultJob = launch {
                println("Default for start")
                for (index in 0..10) {
                    if (isActive) {
                        println("Default index $index")
                        delay(1)
                    } else {
                        break
                    }
                }
                println("Default for end")
            }
            defaultJob.join()
        }
        job.cancel()
        delay(30) // 30ms test only.
    }

//            @Test
//    fun testCoroutines() = runBlocking {
//        CoroutineScope(Dispatchers.Default).launch {
//            delay(1000)
//            println("droid knights!!!")
//        }
//
//        println("Hello")
//        delay(1500)
//    }


    class CoroutineTest : CoroutineScope {
        private val job = SupervisorJob()
        override val coroutineContext: CoroutineContext = job + Dispatchers.Default

        suspend fun test() {
            println("Parent job")
            val childJob = launch {
                println("First child is failing")
                throw Exception("First child is exception")
            }
            val secondJob = launch {
                println("Second child is success")
                delay(500)
                println("Second child is delay 500ms")
            }
            childJob.join()
            secondJob.join()
        }
    }

    @Test
    fun test() = runBlocking {
        CoroutineScope(Dispatchers.IO).launch {
            val jobOne = launch {
                // Job one
                println("job ${Thread.currentThread()}")
            }

            val jobTwo = launch {
                // Job two
                launch(Dispatchers.Main) {
                    println("job ${Thread.currentThread()}")
                    // Use UI Thread
                }
            }

            jobOne.join()
            jobTwo.join()
        }.join()
    }

    @Test
    fun testJob() = runBlocking {
        val job = Job()
        GlobalScope.launch(Dispatchers.Default + job) {
            CoroutineScope(Dispatchers.Default + job).launch {
                println("Job one scope start")
                for (index in 0..20) {
                    if (isActive) {
                        println("Job one scope index $index")
                        delay(1)
                    } else {
                        break
                    }
                }
                println("Job one scope for end")
            }
            val jobTwo = launch {
                println("Job two scope for start")
                for (index in 0..10) {
                    if (isActive) {
                        println("Job two scope index $index")
                        delay(1)
                    } else {
                        break
                    }
                }
                println("Job two scope for end")
            }
            jobTwo.cancelAndJoin()
        }
        delay(1)
        job.cancel()
    }
}


class MyActivity : AppCompatActivity(), CoroutineScope {

    // Job을 등록할 수 있도록 초기화
    private val job: Job = Job()

    // 기본 Main Thread 정의와 job을 함께 초기화
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    // 작업 중이던 모든 job을 종 children을 종료 처리
    override fun onDestroy() {
        super.onDestroy()
        job.cancel() // Cancel job on activity destroy. After destroy all children jobs will be cancelled automatically
    }
}


private suspend fun MutableList<Int>.sum(): Int =
        this.sumBy { it }


