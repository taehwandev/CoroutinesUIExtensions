package tech.thdev.coroutines.ui

class CoroutinesUISendEventTest {

//    @Test
//    fun throttleFirst() = runBlocking {
//        var count = 0
//        val onClick = CoroutinesSendChannelUIEvent<Int, Int>(
//                bgBody = {
//                    it
//                },
//                contextProvider = TestDispatchersProvider)
//        onClick.setThrottleFirst(500, TimeUnit.MILLISECONDS)
//        onClick.consumeEach {
//            println("getCount $it")
//            assert(it == 0)
//        }
//
//        (1..5).forEach { _ ->
//            onClick.offer(count++)
//
//            Thread.sleep(100)
//        }
//    }
//
//    @Test
//    fun throttleLast() = runBlocking {
//        var count = 0
//        val onClick = CoroutinesSendChannelUIEvent<Int, Int>(
//                bgBody = {
//                    println("bgBody $it")
//                    it
//                },
//                contextProvider = TestDispatchersProvider)
//        onClick.throttleLast(500, TimeUnit.MILLISECONDS)
//        onClick.consumeEach {
//            println("getCount $it")
//        }
//
//        (1..5).forEach {  _ ->
//            onClick.offer(++count)
//            println("++count $count")
//            Thread.sleep(100)
//        }
//        Thread.sleep(5000)
//    }
//
//    @Test
//    fun test() {
//
//        val sequence = buildSequence {
//            val start = 0
//            // yielding a single value
//            yield(start)
//            // yielding an iterable
//            yieldAll(1..5 step 2)
//            // yielding an infinite sequence
//            yieldAll(generateSequence(8) { it * 3 })
//        }
//
//        println(sequence.take(7).toList()) // [0, 1, 3, 5, 8, 24, 72]
//        println(sequence.take(9).toList()) // [0, 1, 3, 5, 8, 24, 72]
//        println(sequence.take(20).toList()) // [0, 1, 3, 5, 8, 24, 72]
//
//        fun fibonacci() = buildSequence {
//            var terms = Pair(0, 1)
//
//            // this sequence is infinite
//            while (true) {
//                yield(terms.first)
//                println("first ${terms.first}, second ${terms.second}")
//                terms = Pair(terms.second, terms.first + terms.second)
//            }
//        }
//
//        println(fibonacci().take(10).toList()) // [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
//    }
}