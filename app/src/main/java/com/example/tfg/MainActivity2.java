package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private static final  String URL_cartas = "https://homoiothermal-dears.000webhostapp.com/phpFiles/traerCartas.php";

    List<Cartas> cartasList;
    RecyclerView recyclerCartas;
    Cartas carta;
    AdapterCarta adapterCarta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cartasList = new ArrayList<>();

        loadcartas();
    }
    private void loadcartas(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_cartas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject cartas = array.getJSONObject(i);
                                String imagen = cartas.getString("carta");
                                carta= new Cartas(imagen);
                                cartasList.add(carta);

                            }
                            recyclerCartas = (RecyclerView)findViewById(R.id.recyclerCartas);
                            recyclerCartas.setHasFixedSize(true);
                            recyclerCartas.setLayoutManager(new GridLayoutManager(MainActivity2.this, 2));
                            adapterCarta = new AdapterCarta(MainActivity2.this, cartasList);
                            recyclerCartas.setAdapter(adapterCarta);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
