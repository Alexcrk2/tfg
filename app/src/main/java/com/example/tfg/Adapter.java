package com.example.tfg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<Usuarios> {

    Context context;
    List<Usuarios> arrayusuarios;
    ArrayList<Usuarios> copia = new ArrayList<>();

    public Adapter(@NonNull Context context, List<Usuarios> arrayusuarios) {
        super(context, R.layout.list_usuarios, arrayusuarios);
        this.context = context;
        this.arrayusuarios = arrayusuarios;
        this.copia.addAll(arrayusuarios);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_usuarios, null, true);
        TextView txtID = view.findViewById(R.id.txtid);
        TextView txtNombre = view.findViewById(R.id.txtnombre);

        txtID.setText(arrayusuarios.get(position).getId());
        txtNombre.setText(arrayusuarios.get(position).getUser());

        return view;
    }

    public void filtrar(String texto) {
        // Elimina todos los datos del ArrayList que se cargan en los
        // elementos del adaptador
        arrayusuarios.clear();
// Si no hay texto: agrega de nuevo los datos del ArrayList copiado
        // al ArrayList que se carga en los elementos del adaptador
        if (texto.length() == 0) {
            arrayusuarios.addAll(copia);
        } else {
            // Recorre todos los elementos que contiene el ArrayList copiado
            // y dependiendo de si estos contienen el texto ingresado por el
            // usuario los agrega de nuevo al ArrayList que se carga en los
            // elementos del adaptador.
            for (Usuarios usuarios : copia) {

                if (usuarios.getUser().contains(texto)) {
                    arrayusuarios.add(usuarios);
                }
            }


        }
        notifyDataSetChanged();
    }
}