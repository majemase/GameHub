package com.example.gamehub.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamehub.R;
import com.example.gamehub.controller.Usuario;
import com.example.gamehub.model.ModeloUsuario;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AdaptadorAmigos extends RecyclerView.Adapter<AdaptadorAmigos.ViewHolder> {
    private List<Usuario> dataset;
    private ModeloUsuario modeloUsuario;

    public AdaptadorAmigos(List<Usuario> dataset, Context context) {
        this.dataset = dataset;
        modeloUsuario = new ModeloUsuario(context);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdaptadorAmigos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amigo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAmigos.ViewHolder holder, int position) {
        holder.nickname_tv.setText(dataset.get(position).getNickname());
        holder.amigo_mcv.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Prueba pulsado en un amigo", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nickname_tv;
        MaterialCardView amigo_mcv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname_tv = itemView.findViewById(R.id.amigo_tv);
            amigo_mcv = itemView.findViewById(R.id.amigo_mcv);
        }
    }
}
