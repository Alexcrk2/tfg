package com.example.tfg.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
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
import androidx.annotation.NonNull








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

        //val texto = arguments!!.getString("key")
     //   val textofinal = texto.toString()

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //esto es lo que hay que quitar, hasta el parentesis
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
    fun recogerYMostrar(texto : String){

        val request: StringRequest = object : StringRequest(Method.POST, "https://homoiothermal-dears.000webhostapp.com/phpFiles/traerUser.php",
            Response.Listener { response ->//val jsonArray = JSONArray(response)

                if (!response.isEmpty()) {


                    val jsonArray = JSONArray(response)
                    //val jsonObject = jsonArray[0]
                    for(i in 0 until jsonArray.length()){
                        val txvtid = binding.root.findViewById<TextView>(R.id.textViewID)
                        val txvuser = binding.root.findViewById<TextView>(R.id.textViewUser)
                        val txvemail = binding.root.findViewById<TextView>(R.id.textViewEmail)
                        val txvpass = binding.root.findViewById<TextView>(R.id.textViewPassword)
                        val friend = binding.root.findViewById<TextView>(R.id.userfriend)
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

                        friend.setText("Tu ID para añadir amigos es: " + user +"#"+id)




                    }

                  //  Toast.makeText(this, "Datos del usuario cargados", Toast.LENGTH_SHORT)
                   //     .show()



                } else {

                    //Toast.makeText(this, "No se pudo cargar los datos del usuario ", Toast.LENGTH_SHORT)
                   //     .show()


                }

            }, Response.ErrorListener { error ->
               // Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()

            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["email"] = texto//emailEnviado

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
            ?: // No hay datos, manejar excepción
            return

*/
        val data = this.arguments
        if (data != null) {
            val string2 = data.getString("id")
            if (string2 != null) {
                recogerYMostrar(string2)
            }
        }

        val bundle = activity?.intent?.extras
        val texto: String? = bundle?.getString("id")
        if (texto != null) {
            recogerYMostrar(texto)
        }
    }
}