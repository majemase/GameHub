package com.example.gamehub.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamehub.R;
import com.example.gamehub.controller.Usuario;
import com.example.gamehub.model.ModeloUsuario;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class AdaptadorAddAmigos extends RecyclerView.Adapter<AdaptadorAddAmigos.UsuarioViewHolder> {

    private List<Usuario> listaUsuarios;
    private Context context;

    public AdaptadorAddAmigos(Context context, List<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add_amigo, parent, false);
        return new UsuarioViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        holder.nicknameText.setText(usuario.getNickname());

        holder.btnAgregar.setOnClickListener(v -> {
            String miFirebaseId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            ModeloUsuario modeloUsuario = new ModeloUsuario(context);
            modeloUsuario.enviarSolicitudAmistad(miFirebaseId, usuario.getId_firebase(), new CallBack<String>() {
                @Override
                public void onSuccess(String msg) {
                    Toast.makeText(context, "Solicitud enviada", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(context, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }


    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public void actualizarLista(List<Usuario> nuevaLista) {
        this.listaUsuarios = nuevaLista;
        notifyDataSetChanged();
    }

    static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView nicknameText;
        Button btnAgregar;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            nicknameText = itemView.findViewById(R.id.nickname_tv);
            btnAgregar = itemView.findViewById(R.id.btn_agregar);
        }
    }
}
