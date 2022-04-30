package com.example.tfg

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.HashMap

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val registerButton : Button = findViewById(R.id.buttonR1)

        registerButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                registro()
            }
       })


    }

     private fun registro() {
         val nombre: EditText = findViewById(R.id.userRegistro)
         val email: EditText = findViewById(R.id.emailRegistro)
         val password: EditText = findViewById(R.id.passwordRegistro)

         var Stringnombre = nombre.text.toString()
         var Stringemail = email.text.toString()
         var Stringpass = password.text.toString()
         val progressDialog = ProgressDialog(this)
         progressDialog.setMessage("cargando...")
         if (Stringnombre.isEmpty()) {
             nombre.error = "Campo Nombre incompleto"
             return
         } else if (Stringemail.isEmpty()) {
             email.error = "Campo email vacio"
             return
         } else if (Stringpass.isEmpty()) {
             password.error = "Campo passwors vacio"

         } else {
             progressDialog.show()
             val request: StringRequest = object : StringRequest(
                 Method.POST, "https://homoiothermal-dears.000webhostapp.com/phpFiles/init.php",
                 Response.Listener { response ->
                     if (response.equals("registro realizado correctamente", ignoreCase = true)) {
                         Toast.makeText(this@Registro, "Datos insertados", Toast.LENGTH_SHORT)
                             .show()
                         progressDialog.dismiss()
                         val irAlLogin = Intent(this, Login::class.java).apply {
                         }
                         startActivity(irAlLogin)
                     } else {
                         Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                         progressDialog.dismiss()
                         Toast.makeText(this, "Datos no insertardos", Toast.LENGTH_SHORT)
                             .show()


                     }

                 }, Response.ErrorListener { error ->
                     Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                     progressDialog.dismiss()
                 }) {
                 @Throws(AuthFailureError::class)
                 override fun getParams(): Map<String, String>? {
                     val params: MutableMap<String, String> = HashMap()
                     params["nombre"] = Stringnombre
                     params["email"] = Stringemail
                     params["password"] = Stringpass

                     return params
                 }

             }
             val requestQueue = Volley.newRequestQueue(this)
             requestQueue.add(request)
         }
     }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    //ir al login

}
    fun volverAlLogin(view: android.view.View) {
        val volverAlLogin= Intent(this, Login::class.java).apply {
        }
        startActivity(volverAlLogin)
    }
     }
