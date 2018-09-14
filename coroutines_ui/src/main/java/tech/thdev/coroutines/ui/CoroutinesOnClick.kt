package tech.thdev.coroutines.ui

import android.view.View
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.map

open class CoroutinesOnClick<T>(private val view: View,
                                private val bgBody: suspend (item: View) -> T) {

    private val job: Job = Job()

    fun consumeEach(uiBody: (item: T) -> Unit): CoroutinesOnClick<T> {
        val clickActor: SendChannel<View> = actor(context = UI, parent = job) {
            this.channel.map(context = CommonPool, transform = bgBody).consumeEach(uiBody)
        }

        view.setOnClickListener { clickActor.offer(it) }
        return this
    }
}

fun <T> onClick(view: View, bgBody: suspend (item: View) -> T): CoroutinesOnClick<T> =
        CoroutinesOnClick(view, bgBody)

infix fun <T> CoroutinesOnClick<T>.update(uiBody: (item: T) -> Unit): CoroutinesOnClick<T> =
        this.consumeEach(uiBody)