package com.example.gamehub.Utils;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorAnuncio extends RecyclerView.Adapter<AdaptadorAnuncio.HolderAnuncio> {
    @NonNull
    @Override
    public AdaptadorAnuncio.HolderAnuncio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAnuncio.HolderAnuncio holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HolderAnuncio extends RecyclerView.ViewHolder {
        public HolderAnuncio(@NonNull View itemView) {
            super(itemView);
        }
    }
}
