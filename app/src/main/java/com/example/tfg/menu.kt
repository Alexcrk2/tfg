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
import org.json.JSONObject
import java.util.HashMap

class menu : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val txtid = findViewById<TextView>(R.id.textViewID)
        val txtuser = findViewById<TextView>(R.id.textViewUser)
        val txtemail = findViewById<TextView>(R.id.textViewEmail)
        val txtpass = findViewById<TextView>(R.id.textViewPassword)

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

        val txvtid = findViewById<TextView>(R.id.textViewID)
        val txvuser = findViewById<TextView>(R.id.textViewUser)
        val txvemail = findViewById<TextView>(R.id.textViewEmail)
        val txvpass = findViewById<TextView>(R.id.textViewPassword)

        val tvresultado = findViewById<TextView>(R.id.resultado)
        var datos = intent.extras!!
        val datosobtenidos = datos.getString("id")
        val email =datosobtenidos.toString()
        val request: StringRequest = object : StringRequest(Method.POST, "https://homoiothermal-dears.000webhostapp.com/phpFiles/traerUser.php",
            Response.Listener { response ->//val jsonArray = JSONArray(response)

                if (!response.isEmpty()) {
                    val textview1= findViewById<TextView>(R.id.textViewID)

                    val jsonArray = JSONArray(response)
                    //val jsonObject = jsonArray[0]
                    for(i in 0 until jsonArray.length()){
                        val jsonObject = JSONObject(jsonArray.getString(i))
                        var text1 = jsonObject.get("id")
                        var text2 = jsonObject.get("user")
                        var text3 = jsonObject.get("email")
                        var text4 = jsonObject.get("password")
                        val id = text1.toString()
                        val user = text2.toString()
                        val email = text3.toString()
                        val pass = text4.toString()
                        txvtid.text = id
                        txvuser.text = user
                        txvemail.text = email
                        txvpass.text = pass




                    }

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