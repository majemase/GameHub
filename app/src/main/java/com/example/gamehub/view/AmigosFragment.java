package com.example.gamehub.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gamehub.R;
import com.example.gamehub.Utils.AdaptadorAmigos;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.controller.Usuario;
import com.example.gamehub.model.ModeloUsuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AmigosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AmigosFragment extends Fragment {

    private ModeloUsuario modeloUsuario;
    private SharedPreferences preferences;

    public AmigosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_amigos, container, false);
        RecyclerView amigosRv = view.findViewById(R.id.amigos_rv);
        amigosRv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Usuario> listaInicial = new ArrayList<>();
        AdaptadorAmigos adaptadorAmigos = new AdaptadorAmigos(listaInicial, requireActivity());
        amigosRv.setAdapter(adaptadorAmigos);

        preferences = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String id_firebase = preferences.getString("id_firebase", "");

        FloatingActionButton addAmigo_btn = view.findViewById(R.id.addAmigo_btn);
        addAmigo_btn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddAmigoActivity.class);
            startActivity(intent);
        });

        modeloUsuario = new ModeloUsuario(requireActivity());
        modeloUsuario.obtenerAmigos(id_firebase, new CallBack<List<Usuario>>() {
            @Override
            public void onSuccess(List<Usuario> resultado) {
                AdaptadorAmigos adaptadorAmigos = new AdaptadorAmigos(resultado, requireActivity());
                amigosRv.setAdapter(adaptadorAmigos);
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(getContext(), "Error al cargar amigos", Toast.LENGTH_SHORT).show();
                Log.e("Error", "Error al cargar amigos: " + msg);
            }
        });

        return view;
    }
}