package tech.thdev.coroutinesuiextensions

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.delay
import tech.thdev.coroutines.base.ui.CoroutineScopeAppCompatActivity
import tech.thdev.coroutines.provider.DispatchersProvider

class MainActivity : CoroutineScopeAppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var count = 0
        fab.onClick {
            count++
            for (i in 10 downTo 1) { // countdown from 10 to 1
                tv_message.text = "Now Click $count Countdown $i ..." // update text
                delay(500) // wait half a second
            }
            tv_message.text = "Done!"
        }
    }

    private fun View.onClick(action: suspend (View) -> Unit) {
        // launch one actor
        val event = GlobalScope.actor<View>(DispatchersProvider.main) {
            for (event in channel) action(event)
        }

        setOnClickListener {
            event.offer(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        job.cancel()
    }
}
