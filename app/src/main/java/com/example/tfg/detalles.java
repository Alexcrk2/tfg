package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;

public class detalles extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5;
    ImageView im1;
    String idextra;
    String emailextra;
    String userextra;
    String passextra;
    String imagenextra;

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
        tv2=findViewById(R.id.tnombre);
        tv3=findViewById(R.id.temail);
        tv4=findViewById(R.id.tfriend);
        tv5=findViewById(R.id.nombre2);
        im1=findViewById(R.id.timage);

        Intent intent=getIntent();
        idextra=intent.getStringExtra("id");
        emailextra=intent.getStringExtra("email");
        userextra=intent.getStringExtra("user");
        imagenextra=intent.getStringExtra("foto");



        tv1.setText(idextra);
        tv2.setText(userextra);
        tv3.setText(emailextra);
        tv4.setText("Añadir amigo " + userextra +"#"+idextra);
        tv5.setText(userextra);
        Picasso.get().load(imagenextra).into(im1);

    }
}