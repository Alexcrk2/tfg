package com.example.tfg

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.Navigation

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        music()
        Handler().postDelayed({
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
            music()
        },3000)
    }


    fun music(){
        var mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.musica)
        mediaPlayer.isLooping;
        mediaPlayer.start()
    }

//hola
}
