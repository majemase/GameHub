package com.example.gamehub.view;

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
import com.example.gamehub.Utils.AdaptadorPublicacion;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.controller.Publicacion;
import com.example.gamehub.model.ModeloPublicacion;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisPublicacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisPublicacionesFragment extends Fragment {

    private ModeloPublicacion modeloPublicacion;

    public MisPublicacionesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_mis_publicaciones, container, false);
        RecyclerView publicacionRv = view.findViewById(R.id.misPublicaciones_rv);
        publicacionRv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Publicacion> listaInicial = new ArrayList<>();
        AdaptadorPublicacion adaptadorPublicacion = new AdaptadorPublicacion(listaInicial, getContext());
        publicacionRv.setAdapter(adaptadorPublicacion);

        SharedPreferences preferences = getContext().getSharedPreferences("usuario", getContext().MODE_PRIVATE);

        modeloPublicacion = new ModeloPublicacion(getContext());
        modeloPublicacion.getPublicacionesUsuario(preferences.getString("id_firebase", ""), new CallBack<List<Publicacion>>() {
            @Override
            public void onSuccess(List<Publicacion> resultado) {
                AdaptadorPublicacion adaptadorPublicacion = new AdaptadorPublicacion(resultado, getContext());
                publicacionRv.setAdapter(adaptadorPublicacion);
            }

            @Override
            public void onError(String msg) {
                //Toast.makeText(getContext(), "Error al cargar publicaciones", Toast.LENGTH_SHORT).show();
                Log.e("Error", "Error al cargar publicaciones: " + msg);
            }
        });
        return view;
    }
}