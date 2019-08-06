package tech.thdev.coroutinesuiextensions

import org.junit.Test

/**
 * infix notation
 * Higher-Order function
 */
class Step6 {

    inline infix fun Int.sum(b: Int): Int = this + b

    @Test
    fun test() {
        10.sum(11)
        10 sum 11
    }
}