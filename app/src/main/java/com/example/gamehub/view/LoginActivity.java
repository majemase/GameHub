package com.example.gamehub.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamehub.R;
import com.example.gamehub.controller.Usuario;
import com.example.gamehub.model.Login;

public class LoginActivity extends AppCompatActivity {

    private EditText email_input, pass_input;
    private Button login_btn;
    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email_input = findViewById(R.id.email_input);
        pass_input = findViewById(R.id.pass_input);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(v -> {
            String email = email_input.getText().toString();
            String pass = pass_input.getText().toString();

            login = new Login(this);

            login.loginUsuario(email, pass);
        });
    }

    public void loginCorrecto(Usuario usuario){
        Toast.makeText(this, "Login correcto: " + usuario.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Login", "Login correcto: " + usuario.toString());
    }

    public void loginIncorrecto(String error){
        Toast.makeText(this, "Login incorrecto: "+ error, Toast.LENGTH_SHORT).show();
        Log.e("Error", "Login incorrecto: " + error);
    }
}