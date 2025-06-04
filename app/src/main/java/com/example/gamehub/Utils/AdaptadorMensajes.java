package com.example.gamehub.Utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamehub.R;
import com.example.gamehub.controller.Mensaje;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorMensajes extends RecyclerView.Adapter<AdaptadorMensajes.ViewHolder> {
    private List<Mensaje> dataset;
    private final String id_firebase;

    public AdaptadorMensajes(String id_firebase) {
        this.id_firebase = id_firebase;
        this.dataset = new ArrayList<>();
    }

    public void setDataset(List<Mensaje> mensajes) {
        this.dataset = mensajes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdaptadorMensajes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mensaje, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMensajes.ViewHolder holder, int position) {
        Mensaje mensaje = dataset.get(position);
        holder.contenido_tv.setText(mensaje.getContenido());

        if (mensaje.getIdAutor().equals(id_firebase)) {
            holder.burbuja_ly.setGravity(Gravity.END);
            holder.contenido_tv.setBackgroundResource(R.drawable.burbuja_der);
        } else {
            holder.burbuja_ly.setGravity(Gravity.START);
            holder.contenido_tv.setBackgroundResource(R.drawable.burbuja_izq);
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contenido_tv;
        LinearLayout burbuja_ly;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contenido_tv = itemView.findViewById(R.id.mensaje_tv);
            burbuja_ly = itemView.findViewById(R.id.burbuja_ly);
        }
    }
}
