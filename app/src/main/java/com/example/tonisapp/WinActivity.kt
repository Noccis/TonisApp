package com.example.tonisapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WinActivity : AppCompatActivity() {

    val score = 0  // resettar score till noll om man vill börja om.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        var mediaPlayer = MediaPlayer.create(this, R.raw.win)
        mediaPlayer.start()

        val restartButton = findViewById<Button>(R.id.restartButton)
        restartButton.setOnClickListener {

            restart()
        }


    }

    fun restart() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
    }
}