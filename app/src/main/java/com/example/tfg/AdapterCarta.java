

package com.example.tfg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.List;

public class AdapterCarta extends RecyclerView.Adapter<AdapterCarta.CartasViewHolder> {
    private Context mCtx;
    private List<Cartas> cartasList;


    public AdapterCarta(Context mCtx,
                        List<Cartas> cartasList) {
        this.mCtx = mCtx;
        this.cartasList = cartasList;
    }


    @Override
    public CartasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.disenocarta, parent, false);
        return new CartasViewHolder(view);
    }

    @Override

    public void onBindViewHolder( CartasViewHolder holder, int position) {
        Cartas cartas = cartasList.get(position);
        Glide.with(mCtx)
                .load(cartas.getCarta())
                .into(holder.img_carta);
    }

    @Override
    public int getItemCount() { return cartasList.size(); }

    class CartasViewHolder extends  RecyclerView.ViewHolder {
        ImageView img_carta;
        public CartasViewHolder(View itemView) {
            super(itemView);
            img_carta = itemView.findViewById(R.id.img_carta);
        }
    }

}


