package tech.thdev.coroutinesuiextensions.data

import android.os.Bundle
import android.view.View
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import org.junit.Test

class ContributorTest {

    @Test
    fun test() {
        val sum: Int.(b: Int) -> Int = { this + it }
        10.sum(20)

        val list = mutableListOf(1, "a", 10.0f, 22.00, sum)
        list
                .asSequence()
                .filter {
                    it is Int
                }
                .map {
                    it as Int
                }
                .forEach {
                    println(it.sum(it))
                }

        val name: String? = "11"
        val index = name?.toInt() ?: 0
        println("index $index")
    }

    @Test
    fun coroutinesTest() = runBlocking {
        CoroutineScope(Dispatchers.Default).launch {
            delay(500L)
            println("부산")
        }
        print("Hello, GDG ")
        delay(1000L)
    }
}



