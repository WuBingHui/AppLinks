package com.anthony.app_links

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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

            Log.e("roomId",roomId)
        }

    }

    private fun show(roomId: String) {
        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle("Chooser title")
                .setText("http://anthony.testmachine.org/room_$roomId?id=$roomId")
                .startChooser()
    }

}
