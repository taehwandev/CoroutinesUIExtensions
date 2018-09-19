package tech.thdev.coroutinesuiextensions

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.experimental.Job
import tech.thdev.coroutines.ui.throttleFirst
import tech.thdev.coroutines.ui.update
import tech.thdev.coroutines.ui.viewClick
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val job = Job()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val time = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("mm:ss.SSS", Locale.getDefault())

        viewClick(fab, job = job) {
            it
        }
                .throttleFirst(500)
                .update {
                    val nowTime = System.currentTimeMillis() - time
                    tv_message.text = "onClick ${dateFormat.format(nowTime)}"
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
