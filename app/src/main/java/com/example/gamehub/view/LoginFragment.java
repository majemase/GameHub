package com.example.gamehub.view;

import android.content.Intent;
import android.content.SharedPreferences;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private EditText email_input, pass_input;
    private Button login_btn;
    private ModeloUsuario login;

    public LoginFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences preferences = requireActivity().getSharedPreferences("usuario", getContext().MODE_PRIVATE);
        if(preferences.getBoolean("mantener_sesion", false)){
            Intent intent = new Intent(getContext(), HomeActivity.class);
            startActivity(intent);
            requireActivity().finish();
        }


        email_input = view.findViewById(R.id.email_input);
        pass_input = view.findViewById(R.id.pass_input);
        login_btn = view.findViewById(R.id.login_btn);

        login_btn.setOnClickListener(v -> {
            String email = email_input.getText().toString();
            String pass = pass_input.getText().toString();

            if(!email.isEmpty() || !pass.isEmpty()){
                login = new ModeloUsuario(requireActivity());

                login.loginUsuario(email, pass, new CallBack<Usuario>(){

                    @Override
                    public void onSuccess(Usuario usuario) {
                        Intent intent = new Intent(getContext(), HomeActivity.class);
                        login.guardarUsuario(usuario);
                        startActivity(intent);
                        requireActivity().finish();
                        Toast.makeText(getContext(), "Login correcto", Toast.LENGTH_SHORT).show();
                        Log.i("Login", "Login correcto: " + usuario.toString());
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getContext(), "Login incorrecto revise las credenciales", Toast.LENGTH_SHORT).show();
                        Log.e("Error", "Login incorrecto: " + error);
                    }
                });
            }else{
                Toast.makeText(getContext(), "Por favor complete los campos", Toast.LENGTH_LONG).show();
            }
        });
    }
}