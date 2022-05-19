package com.example.tfg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Adapter adapter;
    public static ArrayList<Usuarios>usuariosArrayList=new ArrayList<>();
    String url="https://homoiothermal-dears.000webhostapp.com/phpFiles/list.php";
    Usuarios usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listMostrar);
        adapter=new Adapter(this,usuariosArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());


                CharSequence[] dialogoItem ={"Ver datos", "Editar datos", "Eliminar datos"};
                builder.setTitle(usuariosArrayList.get(position).getUser());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),detalles.class).
                                        putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),editar.class).
                                        putExtra("position",position));
                                break;
                            case 2:
                              // validando por id, mirar get nombre?  EliminarDatos(usuariosArrayList.get(position).getId());
                                break;



                        }
                    }
                });
                builder.create().show();
            }
        });
        ListarDatos();
    }
    private void ListarDatos(){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                usuariosArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String exito = jsonObject.getString("exito");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if (exito.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);


                            String id = object.getString("id");
                            String user = object.getString("user");
                            String email = object.getString("email");
                            String password = object.getString("password");
                            usuarios = new Usuarios(id, user, email, password);
                            usuariosArrayList.add(usuarios);
                            adapter.notifyDataSetChanged();

                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
    public void volver(View view) {
        startActivity(new Intent(getApplicationContext(),menu.class));
    }

}