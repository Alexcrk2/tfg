package com.example.tfg.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tfg.AdapterCarta
import com.example.tfg.Cartas
import com.example.tfg.R
import com.example.tfg.databinding.FragmentNotificationsBinding
import org.json.JSONArray
import org.json.JSONException

class NotificationsFragment : Fragment() {
    var cartasList: MutableList<Cartas>? = null
    var recyclerCartas: RecyclerView? = null
    var carta: Cartas? = null
    var adapterCarta: AdapterCarta? = null
    private val URL_cartas =
        "https://homoiothermal-dears.000webhostapp.com/phpFiles/traerCartas.php"
    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       cartasList = mutableListOf()

        loadcartas()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
    private fun loadcartas() {
        val stringRequest = StringRequest(Request.Method.GET, URL_cartas,
            { response ->
                try {
                    val array = JSONArray(response)
                    for (i in 0 until array.length()) {
                        val cartas = array.getJSONObject(i)
                        val imagen = cartas.getString("carta")
                        carta = Cartas(imagen)
                            cartasList?.add(carta!!)
                    }
                    recyclerCartas = binding.root.findViewById<View>(R.id.recyclerCartas) as RecyclerView
                    recyclerCartas!!.setHasFixedSize(true)
                    recyclerCartas!!.layoutManager = GridLayoutManager(context, 2)
                    adapterCarta = AdapterCarta(context, cartasList)
                    recyclerCartas!!.adapter = adapterCarta
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { }
        Volley.newRequestQueue(context).add(stringRequest)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}