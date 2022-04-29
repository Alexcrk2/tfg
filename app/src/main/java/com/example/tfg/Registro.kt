package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
    }
    //ir al login
    fun iralLogin(view: android.view.View) {
        val iralLogin = Intent(this, Login::class.java).apply {
        }
        startActivity(iralLogin)
    }
}