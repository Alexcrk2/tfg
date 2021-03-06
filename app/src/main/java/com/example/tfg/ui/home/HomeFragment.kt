package com.example.tfg.ui.home

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tfg.R
import com.example.tfg.databinding.FragmentHomeBinding
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.tfg.Login
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso


class HomeFragment : Fragment() {

    var result : String? = null
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    var txvtid: TextView? = null
    var txvuser:TextView? = null
    var txvemail:TextView? = null
    var txvpass:TextView? = null
    var friend:TextView? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        //val texto = arguments!!.getString("key")
        //   val textofinal = texto.toString()
//prueba comit
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //esto es lo que hay que quitar, hasta el parentesis
        val textView: TextView = binding.textViewEmail
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val logoutButton : FloatingActionButton = binding.root.findViewById(R.id.botonlogout)
        logoutButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                val intent = Intent(activity, Login::class.java)
                activity!!.startActivity(intent)
                Toast.makeText(this@HomeFragment.requireActivity(), "Has cerrado sesi??n", Toast.LENGTH_SHORT).show()

            }
        })
        return root
    }

    fun recogerYMostrar(texto : String){

        val request: StringRequest = object : StringRequest(Method.POST, "https://dianoetic-adhesives.000webhostapp.com/phpFiles/traerUser.php",
            Response.Listener { response ->//val jsonArray = JSONArray(response)

                if (!response.isEmpty()) {


                    val jsonArray = JSONArray(response)
                    //val jsonObject = jsonArray[0]
                    for(i in 0 until jsonArray.length()){
                        var campoimagen = binding.root.findViewById<ImageView>(R.id.imageView)

                        val txvtid = binding.root.findViewById<TextView>(R.id.textViewID)
                        val txvuser = binding.root.findViewById<TextView>(R.id.textViewUser)
                        val txvuser2 = binding.root.findViewById<TextView>(R.id.textViewUser2)
                        val txvemail = binding.root.findViewById<TextView>(R.id.textViewEmail)
                        val txvpass = binding.root.findViewById<TextView>(R.id.textViewPassword)
                        val friend = binding.root.findViewById<TextView>(R.id.friend)
                        val jsonObject = JSONObject(jsonArray.getString(i))
                        var text1 = jsonObject.get("id")
                        var text2 = jsonObject.get("nombre")
                        var text3 = jsonObject.get("email")
                        var text4 = jsonObject.get("password")
                        var text5 = jsonObject.get("foto")
                        // val image2 = image.toString()
                        //  val bytecode: ByteArray = Base64.decode(image2, Base64.DEFAULT)
                        //   var imagen2: Bitmap = BitmapFactory.decodeByteArray(bytecode, 0, bytecode.size)
                        //   campoimagen.setImageBitmap(imagen2)



                        val id = text1.toString()
                        val user = text2.toString()
                        val email = text3.toString()
                        val pass = text4.toString()
                        val foto = text5.toString()
                        Picasso.get().load(foto).into(campoimagen)
                        txvtid.text = id
                        txvuser.text = user
                        txvemail.text = email
                        txvpass.text = pass
                        txvuser2.setText(user)
                        friend.setText("Tu ID para a??adir amigos es: " + user +"#"+id)




                    }
                    Toast.makeText(this@HomeFragment.requireActivity(), "datos cargados", Toast.LENGTH_SHORT).show()
                    //  Toast.makeText(this, "Datos del usuario cargados", Toast.LENGTH_SHORT)
                    //     .show()



                } else {

                    //Toast.makeText(this, "No se pudo cargar los datos del usuario ", Toast.LENGTH_SHORT)
                    //     .show()
                    Toast.makeText(this@HomeFragment.requireActivity(), "no se pudieron cargar los datos", Toast.LENGTH_SHORT).show()

                }

            }, Response.ErrorListener { error ->
                // Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                Toast.makeText(this@HomeFragment.requireActivity(), "tiempo de respuesta excecido", Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["email"] = texto

                return params
            }

        }
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(request)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*val datosRecuperados = arguments
            ?: // No hay datos, manejar excepci??n
            return

*/


        val bundle = activity?.intent?.extras
        val texto: String? = bundle?.getString("id")
        if (texto != null) {
            recogerYMostrar(texto)
        }

    }
}