package tech.thdev.coroutinesuiextensions

import org.junit.Test

/**
 * 1. property 설명
 * 2. NotNull, Nullable 설명
 * 3. String template
 */
class Step1 {

//    String name = "";
//    final String name = "";

    var firstName: String? = "taehwan"
    val lastName: String = "kwon"

    @Test
    fun testStepOne() {
        println("$firstName ${lastName.length}")
        println(firstName + " " + lastName)
        firstName = null
    }
}