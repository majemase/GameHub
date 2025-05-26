package com.example.gamehub.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gamehub.R;
import com.example.gamehub.controller.Usuario;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.model.ModeloUsuario;

import java.time.LocalDateTime;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {

    private EditText nombre_input, nickname_input, email_input, pass_input;
    private Button registro_btn;
    private ModeloUsuario registro;

    public RegistroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nombre_input = view.findViewById(R.id.nombre_input);
        nickname_input = view.findViewById(R.id.nickname_input);
        email_input = view.findViewById(R.id.email_input);
        pass_input = view.findViewById(R.id.pass_input);
        registro_btn = view.findViewById(R.id.login_btn);

        registro_btn.setOnClickListener(v -> {

            String nombre = nombre_input.getText().toString();
            String nickname = nickname_input.getText().toString();
            String email = email_input.getText().toString();
            String pass = pass_input.getText().toString();

            if(!nombre.isEmpty() || !nickname.isEmpty() || !email.isEmpty() || !pass.isEmpty()){
                registro = new ModeloUsuario(requireActivity());

                Usuario usuario = new Usuario(0, nombre, email, pass, "", nickname, "", LocalDateTime.now());

                registro.registrarUsuario(usuario, new CallBack<Usuario>(){

                    @Override
                    public void onSuccess(Usuario usuario) {
                        Toast.makeText(getContext(), "Registro correcto", Toast.LENGTH_SHORT).show();
                        Log.i("Registro", "Registro correcto");
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getContext(), "Registro incorrecto", Toast.LENGTH_SHORT).show();
                        Log.e("Error", "Registro incorrecto: " + error);
                    }
                });
            }else{
                Toast.makeText(getContext(), "Por favor complete los campos", Toast.LENGTH_LONG).show();
            }
        });
    }
}