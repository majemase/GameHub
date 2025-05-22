package com.example.gamehub.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamehub.R;
import com.example.gamehub.controller.Publicacion;

import java.util.List;

public class AdaptadorPublicacion extends RecyclerView.Adapter<AdaptadorPublicacion.HolderPublicacion> {

    private List<Publicacion> dataset;

    public AdaptadorPublicacion(List<Publicacion> dataset) {
        this.dataset = dataset;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdaptadorPublicacion.HolderPublicacion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publicacion, parent, false);
        return new HolderPublicacion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPublicacion.HolderPublicacion holder, int position) {
        Publicacion p = dataset.get(position);

        holder.autor_tv.setText(p.getAutor().getNickname());
        holder.contenido_tv.setText(p.getTexto());
        holder.numLikes_tv.setText(String.valueOf(p.getNumLikes()));
        holder.numComentarios_tv.setText(String.valueOf(p.getNumComentarios()));

        holder.like_btn.setOnClickListener(v -> {
            p.setNumLikes(p.getNumLikes() + 1);
            holder.numLikes_tv.setText(String.valueOf(p.getNumLikes()));
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class HolderPublicacion extends RecyclerView.ViewHolder {

        TextView autor_tv, contenido_tv, numLikes_tv, numComentarios_tv;
        ImageButton like_btn, comentario_btn;

        public HolderPublicacion(@NonNull View itemView) {
            super(itemView);
            autor_tv = itemView.findViewById(R.id.autor_tv);
            contenido_tv = itemView.findViewById(R.id.contenido_tv);
            numLikes_tv = itemView.findViewById(R.id.numLikes_tv);
            numComentarios_tv = itemView.findViewById(R.id.numComentarios_tv);
            like_btn = itemView.findViewById(R.id.like_btn);
            comentario_btn = itemView.findViewById(R.id.comentario_btn);
        }
    }
}
