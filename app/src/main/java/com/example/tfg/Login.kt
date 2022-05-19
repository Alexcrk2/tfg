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

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val registerButton : Button = findViewById(R.id.buttonL1)
        registerButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                login()
            }
        })
    }

    private fun login(){
        val email: EditText = findViewById(R.id.emailLogin)
        val password: EditText = findViewById(R.id.passwordLogin)
        var Stringemail = email.text.toString()
        var Stringpass = password.text.toString()

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("cargando...")
         if (Stringemail.isEmpty()) {
            email.error = "Campo email vacio"
            return
        } else if (Stringpass.isEmpty()) {
            password.error = "Campo password vacio"

        }else {
             progressDialog.show()
             val request: StringRequest = object : StringRequest(
                 Method.POST, "https://homoiothermal-dears.000webhostapp.com/phpFiles/login.php",
                 Response.Listener { response ->
                     if (!response.isEmpty()) {
                         email.setText("")
                         password.setText("")
                         progressDialog.dismiss()

                         val irAlmenu = Intent(this, menu::class.java).apply {
                         }
                         startActivity(irAlmenu)

                         val intent = Intent(this, menu::class.java)
                         intent.putExtra("id", Stringemail)
                         startActivity(intent)

                     } else {
                         Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                         progressDialog.dismiss()
                         Toast.makeText(this, "No se pudo iniciar sesión", Toast.LENGTH_SHORT)
                             .show()


                     }

                 }, Response.ErrorListener { error ->
                     Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                     progressDialog.dismiss()
                 }) {
                 @Throws(AuthFailureError::class)
                 override fun getParams(): Map<String, String>? {
                     val params: MutableMap<String, String> = HashMap()
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


    }
    fun irAlRegistro(view: android.view.View) {
        val irAlRegistro= Intent(this, Registro::class.java).apply {
        }
        startActivity(irAlRegistro)
    }
    }




