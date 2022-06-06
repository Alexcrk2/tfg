package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class detalles extends AppCompatActivity {
TextView tv1, tv2, tv3, tv4;
int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        this.getWindow().setLayout((int)(width * .8), (int)(height * .7));

        tv1=findViewById(R.id.tid);
        tv2=findViewById(R.id.tuser);
        tv3=findViewById(R.id.temail);
        tv4=findViewById(R.id.tpassword);

        Intent intent=getIntent();
        position = intent.getExtras().getInt("position");

        tv1.setText("ID " + MainActivity.usuariosArrayList.get(position).getId());
        tv2.setText("Nombre " + MainActivity.usuariosArrayList.get(position).getUser());
        tv3.setText("Email " + MainActivity.usuariosArrayList.get(position).getEmail());
        tv4.setText("Contraseña " + MainActivity.usuariosArrayList.get(position).getPassword());

    }
}