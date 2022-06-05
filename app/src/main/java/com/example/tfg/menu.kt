package com.example.tfg

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class menu : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


       // val mostrardatos = findViewById<TextView>(R.id.extra)
      //  var datos = intent.extras!!
      //  val datosobtenidos = datos.getString("id")
      //  mostrardatos.setText(datosobtenidos)

        var campoimagen = findViewById<ImageView>(R.id.imageView)
        val txtid = findViewById<TextView>(R.id.textViewID)
        val txtuser = findViewById<TextView>(R.id.textViewUser)
        val txtemail = findViewById<TextView>(R.id.textViewEmail)
        val txtpass = findViewById<TextView>(R.id.textViewPassword)

        //val tvresultado = findViewById<TextView>(R.id.resultado)
        val queue = Volley.newRequestQueue(this)
        val url = "https://homoiothermal-dears.000webhostapp.com/phpFiles/fotoPerfil.php"
        val stringRequest = StringRequest(Request.Method.GET,url, { response -> //val jsonArray = JSONArray(response)
            val jsonArray = JSONArray(response)
            //val jsonObject = jsonArray[0]
            for(i in 0 until jsonArray.length()){

                val jsonObject = JSONObject(jsonArray.getString(i))
                var text1 = jsonObject.get("id")
                var text2 = jsonObject.get("nombre")
                var text3 = jsonObject.get("email")
                var text4 = jsonObject.get("password")
                var image = jsonObject.get("foto")
              //  val image2 = image.toString()
               // val bytecode: ByteArray = Base64.decode(image2, Base64.DEFAULT)

               // var imagen2: Bitmap = BitmapFactory.decodeByteArray(bytecode, 0, bytecode.size)
                val id = text1.toString()
                val user = text2.toString()
                val email = text3.toString()
                val pass = text4.toString()
              //  campoimagen.setImageBitmap(imagen2)
                txtid.text = id
                txtuser.text = user
                txtemail.text = email
                txtpass.text = pass

            }
            }, {  })
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