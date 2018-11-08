package tech.thdev.coroutinesuiextensions

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
import tech.thdev.coroutines.ui.onClick
import tech.thdev.coroutines.ui.runUi
import tech.thdev.coroutinesuiextensions.network.RetrofitFactory
import tech.thdev.support.base.coroutines.ui.CoroutineScopeActivity

class MainActivity : CoroutineScopeActivity() {

    private val gitHubService by lazy {
        RetrofitFactory.githubApi
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.onClick(this) {
            gitHubService.contributors(tv_owner.text.toString(), tv_repo.text.toString())
                    .onErrorReturn {
                        mutableListOf()
                    }
                    .await()
        }.runUi {
            if (it.isNotEmpty()) {
                for ((name, contributions) in it) {
                    tv_message.text = "$name as $contributions contributions!"
                }
            } else {
                tv_message.text = "Not found"
            }
        }

        launch {
            delay(1000)
            Toast.makeText(this@MainActivity, "ShowToast", Toast.LENGTH_SHORT).show()
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
}
