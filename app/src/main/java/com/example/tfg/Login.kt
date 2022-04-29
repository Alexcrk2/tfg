package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
    //para ir al registro
    fun Alregistro(view: android.view.View) {
        val Alregistro = Intent(this, Registro::class.java).apply {
        }
        startActivity(Alregistro)
    }
}