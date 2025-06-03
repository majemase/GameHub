package com.example.gamehub.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamehub.R;
import com.example.gamehub.controller.Comentario;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdaptadorComentario extends RecyclerView.Adapter<AdaptadorComentario.HolderComentario> {

    private List<Comentario> dataset;
    private Context context;

    public AdaptadorComentario(List<Comentario> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderComentario onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        return new HolderComentario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderComentario holder, int position) {
        Comentario c = dataset.get(position);
        holder.autor_tv.setText(c.getAutor().getNickname());
        holder.contenido_tv.setText(c.getContenido());

        if (c.getFecha_creacion() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            holder.fecha_tv.setText(c.getFecha_creacion().format(formatter));
        } else {
            holder.fecha_tv.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class HolderComentario extends RecyclerView.ViewHolder {
        TextView autor_tv, contenido_tv, fecha_tv;

        public HolderComentario(@NonNull View itemView) {
            super(itemView);
            autor_tv = itemView.findViewById(R.id.autorComentario_tv);
            contenido_tv = itemView.findViewById(R.id.contenidoComentario_tv);
            fecha_tv = itemView.findViewById(R.id.fechaComentario_tv);
        }
    }
}
