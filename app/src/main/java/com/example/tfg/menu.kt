package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import java.util.HashMap

class menu : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        //  val mostrardatos = findViewById<TextView>(R.id.extra)
        //  var datos = intent.extras!!
        //val datosobtenidos = datos.getString("id")
        // val email =datosobtenidos.toString()

        // mostrardatos.setText(datosobtenidos)



        //val tvresultado = findViewById<TextView>(R.id.resultado)
        //val queue = Volley.newRequestQueue(this)
        //val url = "https://homoiothermal-dears.000webhostapp.com/phpFiles/testlogin/jsonconsulta.php"
        //val stringRequest = StringRequest(Request.Method.GET,url,Response.Listener { response -> val jsonArray = JSONArray(response)
        //   val jsonObject = jsonArray[0]
        // tvresultado.text = jsonObject.toString()
        //   },Response.ErrorListener {  })
        //   queue.add(stringRequest)

        recogerYMostrar()
    }
    fun recogerYMostrar(){
        val tvresultado = findViewById<TextView>(R.id.resultado)
        var datos = intent.extras!!
        val datosobtenidos = datos.getString("id")
        val email =datosobtenidos.toString()
        val request: StringRequest = object : StringRequest(Method.POST, "https://homoiothermal-dears.000webhostapp.com/phpFiles/traerUser.php",
            Response.Listener { response ->val jsonArray = JSONArray(response)

                if (!response.isEmpty()) {

                    val jsonObject = jsonArray[0]
                    tvresultado.text = jsonObject.toString()
                    Toast.makeText(this, "LETS GOOOO", Toast.LENGTH_SHORT)
                        .show()



                } else {

                    Toast.makeText(this, "RIPAZO BROOO", Toast.LENGTH_SHORT)
                        .show()


                }

            }, Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()

            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["email"] = email

                return params
            }

        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)

    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()


    }
    fun AM(view: android.view.View) {
        val AM= Intent(this, MainActivity::class.java).apply {
        }
        startActivity(AM)
    }



}