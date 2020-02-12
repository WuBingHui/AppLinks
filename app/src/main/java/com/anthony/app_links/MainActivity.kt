package com.anthony.app_links

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        handleIntent(intent)

        button.setOnClickListener { show("1") }
        button2.setOnClickListener { show("2") }
        button3.setOnClickListener { show("3") }
        button4.setOnClickListener { show("4") }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {

        val uri  = intent.data

        uri?.let {

            val roomId = it.getQueryParameter("id")

            roomId?.let {
                showRoom(it,it)
            }

        }

    }

    private fun show(roomId: String) {
        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle("Chooser title")
            .setText("http://anthony.testmachine.org/room?id=$roomId")
                .startChooser()
    }

    private fun showRoom(roomId: String, id: String) {

        val intent = Intent()

        intent.putExtra("id", id)
        intent.putExtra("roomId", roomId)
        intent.flags = FLAG_ACTIVITY_SINGLE_TOP
        intent.setClass(this, MainActivity::class.java)
        when (roomId) {
            "1" -> intent.setClass(this, MainActivity::class.java)
            "2" -> intent.setClass(this, Main2Activity::class.java)
            "3" -> intent.setClass(this, Main3Activity::class.java)
            "4" -> intent.setClass(this, Main4Activity::class.java)
        }
        startActivity(intent)

    }

}
