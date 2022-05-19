package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class menu : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val mostrardatos = findViewById<TextView>(R.id.extra)
        var datos = intent.extras!!
        val datosobtenidos = datos.getString("id")
        mostrardatos.setText(datosobtenidos)



        val tvresultado = findViewById<TextView>(R.id.resultado)
        val queue = Volley.newRequestQueue(this)
        val url = "https://homoiothermal-dears.000webhostapp.com/phpFiles/testlogin/jsonconsulta.php"
        val stringRequest = StringRequest(Request.Method.GET,url,Response.Listener { response -> val jsonArray = JSONArray(response)
        val jsonObject = jsonArray[0]
            tvresultado.text = jsonObject.toString()
        },Response.ErrorListener {  })
        queue.add(stringRequest)

        recogerYMostrar()
    }
    fun AM(view: android.view.View) {
        val AM= Intent(this, MainActivity::class.java).apply {
        }
        startActivity(AM)
    }


    fun recogerYMostrar(){

    }
}