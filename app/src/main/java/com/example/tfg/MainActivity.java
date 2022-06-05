package com.example.tfg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView ImageView;
    EditText buscar;
    ListView listView;
    Adapter adapter;

    SearchView searchView;
    public static ArrayList<Usuarios>usuariosArrayList=new ArrayList<>();
    String url="https://homoiothermal-dears.000webhostapp.com/phpFiles/list.php";
    Usuarios usuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        adapter=new Adapter(this,usuariosArrayList);

        ListarDatos(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //searchView=findViewById(R.id.barraBusqueda);
        listView=findViewById(R.id.listMostrar);

        listView.setAdapter(adapter);


        //este listView.setOnItemClickListener(new AdapterView.OnItemClickListener() permite que cuando le demos a un elemento de la lista pase algo
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

        ListarDatos(this);


    }
    private void ListarDatos(Context context){
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


                        }
                        adapter=new Adapter(context,usuariosArrayList);

                        //la busqueda de mierda, ver.
                        buscar = findViewById(R.id.buscar);
                        buscar.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i0, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence , int i, int i0, int i2) {
                                adapter.filtrar(buscar.getText().toString()) ;
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                //adapter.filtrar(buscar.getText().toString());
                                adapter.filtrar(buscar.getText().toString()) ;
                                adapter.notifyDataSetChanged();

                            }
                        });
                        adapter.notifyDataSetChanged();
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
    public void cargarImagen(){

        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicación"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path=data.getData();
            ImageView.setImageURI(path);
            try {
                Bitmap bmp = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),path);
                ImageView.setImageBitmap(bmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String convertirImgString(Bitmap bitmap){
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imageByte = array.toByteArray();
        String imagenString = Base64.encodeToString(imageByte,Base64.DEFAULT);

        return imagenString;
    }
}