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
import com.example.gamehub.model.Login;
import com.example.gamehub.Utils.CallBack;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private EditText email_input, pass_input;
    private Button login_btn;
    private Login login;

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

        email_input = view.findViewById(R.id.email_input);
        pass_input = view.findViewById(R.id.pass_input);
        login_btn = view.findViewById(R.id.login_btn);

        login_btn.setOnClickListener(v -> {
            String email = email_input.getText().toString();
            String pass = pass_input.getText().toString();

            login = new Login(requireActivity());

            login.loginUsuario(email, pass, new CallBack<Usuario>(){

                @Override
                public void onSuccess(Usuario usuario) {
                    Toast.makeText(getContext(), "Login correcto: " + usuario.toString(), Toast.LENGTH_SHORT).show();
                    Log.i("Login", "Login correcto: " + usuario.toString());
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(getContext(), "Login incorrecto: " + error, Toast.LENGTH_SHORT).show();
                    Log.e("Error", "Login incorrecto: " + error);
                }
            });
        });
    }
}