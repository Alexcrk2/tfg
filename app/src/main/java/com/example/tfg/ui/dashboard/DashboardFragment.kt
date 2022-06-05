package com.example.tfg.ui.dashboard

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tfg.*
import com.example.tfg.Adapter
import com.example.tfg.databinding.FragmentDashboardBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var buscar: EditText? = null
    var listView: ListView? = null
    var adapter: Adapter? = null
    var searchView: SearchView? = null
    var usuariosArrayList = ArrayList<Usuarios>()
    var url = "https://homoiothermal-dears.000webhostapp.com/phpFiles/list.php"
    var usuarios: Usuarios? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val context: Context? = activity
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root




        if(context != null){
            adapter = Adapter(context, MainActivity.usuariosArrayList)

            ListarDatos(context)
        }

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //searchView=findViewById(R.id.barraBusqueda);
        //searchView=findViewById(R.id.barraBusqueda);
        listView = binding.root.findViewById(R.id.listMostrar)

        listView!!.adapter = adapter


        //este listView.setOnItemClickListener(new AdapterView.OnItemClickListener() permite que cuando le demos a un elemento de la lista pase algo


        //este listView.setOnItemClickListener(new AdapterView.OnItemClickListener() permite que cuando le demos a un elemento de la lista pase algo
        listView!!.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val builder = AlertDialog.Builder(view.context)
                val progressDialog = ProgressDialog(view.context)
                val dialogoItem =
                    arrayOf<CharSequence>("Ver datos", "Editar datos", "Eliminar datos")
                builder.setTitle(MainActivity.usuariosArrayList[position].user)
                builder.setItems(
                    dialogoItem
                ) { dialog, i ->
                    when (i) {
                        0 -> startActivity(
                            Intent(context
                                ,
                                detalles::class.java
                            ).putExtra("position", position)
                        )
                        1 -> startActivity(
                            Intent(
                                context,
                                editar::class.java
                            ).putExtra("position", position)
                        )
                        2 -> {
                        }
                    }
                }
                builder.create().show()
            }

        ListarDatos(context)


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun ListarDatos(context: Context?) {
        val request = StringRequest(
            Request.Method.POST, url,
            { response ->
                MainActivity.usuariosArrayList.clear()
                try {
                    if(context != null){
                        val jsonObject = JSONObject(response)
                        val exito = jsonObject.getString("exito")
                        val jsonArray = jsonObject.getJSONArray("datos")
                        if (exito == "1") {
                            for (i in 0 until jsonArray.length()) {
                                val `object` = jsonArray.getJSONObject(i)
                                val id = `object`.getString("id")
                                val user = `object`.getString("user")
                                val email = `object`.getString("email")
                                val password = `object`.getString("password")
                                usuarios = Usuarios(id, user, email, password)
                                MainActivity.usuariosArrayList.add(usuarios)
                            }
                            adapter = Adapter(context, MainActivity.usuariosArrayList)

                            //la busqueda de mierda, ver.
                            buscar = binding.root.findViewById<EditText>(R.id.buscar)
                            buscar!!.addTextChangedListener(object : TextWatcher {
                                override fun beforeTextChanged(
                                    charSequence: CharSequence,
                                    i: Int,
                                    i0: Int,
                                    i2: Int
                                ) {
                                    adapter!!.filtrar(buscar!!.text.toString())
                                    adapter!!.notifyDataSetChanged()
                                }

                                override fun onTextChanged(
                                    charSequence: CharSequence,
                                    i: Int,
                                    i0: Int,
                                    i2: Int
                                ) {
                                    adapter!!.filtrar(buscar!!.text.toString())
                                    adapter!!.notifyDataSetChanged()
                                }

                                override fun afterTextChanged(editable: Editable) {
                                    //adapter.filtrar(buscar.getText().toString());
                                    adapter!!.filtrar(buscar!!.text.toString())
                                    adapter!!.notifyDataSetChanged()
                                }
                            })
                            adapter!!.notifyDataSetChanged()
                        }
                    }


                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(request)
    }

    fun volver(view: View?) {
        startActivity(Intent(context, menu::class.java))
    }

}