package com.example.gamehub.view;

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
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.controller.Publicacion;
import com.example.gamehub.model.AdaptadorPublicacion;
import com.example.gamehub.model.ModeloHome;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private ModeloHome modeloHome;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView publicacionRv = view.findViewById(R.id.home_rv);
        publicacionRv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Publicacion> listaInicial = new ArrayList<>();
        AdaptadorPublicacion adaptadorPublicacion = new AdaptadorPublicacion(listaInicial);
        publicacionRv.setAdapter(adaptadorPublicacion);

        modeloHome = new ModeloHome(requireActivity());
        modeloHome.getAllPublicaciones(new CallBack<List<Publicacion>>() {
            @Override
            public void onSuccess(List<Publicacion> resultado) {
                AdaptadorPublicacion adaptadorPublicacion = new AdaptadorPublicacion(resultado);
                publicacionRv.setAdapter(adaptadorPublicacion);
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(getContext(), "Error al cargar publicaciones", Toast.LENGTH_SHORT).show();
                Log.e("Error", "Error al cargar publicaciones: " + msg);
            }
        });
        return view;
    }

}