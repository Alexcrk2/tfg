package com.example.tfg

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.HashMap
import android.graphics.Bitmap
import android.util.Base64
import androidx.core.graphics.drawable.toBitmap
import java.io.ByteArrayOutputStream


class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        supportActionBar?.hide()

        val registerButton : Button = findViewById(R.id.buttonR1)
        // val ImageView = findViewById<ImageView>(R.id.imageView)
        registerButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                registro()
            }
       })
        val botonFoto : Button = findViewById(R.id.agregarfoto)
        botonFoto.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                cargarImagen()
            }
        })


    }


    fun cargarImagen() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/"
        startActivityForResult(Intent.createChooser(intent, "Seleccione la aplicación"), 10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val ImageView = findViewById<ImageView>(R.id.imageView)
        if (resultCode == RESULT_OK) {
            val path = data!!.data
            ImageView.setImageURI(path)

        }
    }
    fun getStringImagen(bmp: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

     fun registro() {
         val ImageView = findViewById<ImageView>(R.id.imageView)
         val nombre: EditText = findViewById(R.id.userRegistro)
         val email: EditText = findViewById(R.id.emailRegistro)
         val password: EditText = findViewById(R.id.passwordRegistro)

         val imagenurl = ImageView.drawable
         val bmap: Bitmap = imagenurl.toBitmap()
         val bos = ByteArrayOutputStream()
         bmap.compress(Bitmap.CompressFormat.JPEG,100,bos)
         val bb = bos.toByteArray()
         val image: String = Base64.encodeToString(bb, Base64.DEFAULT)


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
                 Method.POST, "https://dianoetic-adhesives.000webhostapp.com/phpFiles/registro.php",
                 Response.Listener { response ->
                     if (response.equals("registro realizado correctamente", ignoreCase = true)) {
                         Toast.makeText(this, "Datos insertados", Toast.LENGTH_SHORT)
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
                     params["foto"] = image
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

    fun volverAlLogin(view: android.view.View) {
        val volverAlLogin= Intent(this, Login::class.java).apply {
        }
        startActivity(volverAlLogin)
    }



}
