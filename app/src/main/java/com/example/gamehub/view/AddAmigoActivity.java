package com.example.gamehub.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamehub.R;
import com.example.gamehub.Utils.AdaptadorAddAmigos;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.controller.Usuario;
import com.example.gamehub.model.ModeloUsuario;

import java.util.ArrayList;
import java.util.List;

public class AddAmigoActivity extends AppCompatActivity {

    private ModeloUsuario modeloUsuario;
    private List<Usuario> listaUsuarios;
    private AdaptadorAddAmigos adaptadorUsuario;
    private RecyclerView recyclerUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_amigo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setContentView(R.layout.activity_add_amigo);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        modeloUsuario = new ModeloUsuario(this);
        Button btnBuscar = findViewById(R.id.buscar_btn);
        EditText etBuscar = findViewById(R.id.buscador_et);
        recyclerUsuarios = findViewById(R.id.usuarios_rv);

        listaUsuarios = new ArrayList<>();
        adaptadorUsuario = new AdaptadorAddAmigos(this, listaUsuarios);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this));
        recyclerUsuarios.setAdapter(adaptadorUsuario);

        btnBuscar.setOnClickListener(v -> {
            String nickname = etBuscar.getText().toString().trim(); // CORREGIDO
            if (!nickname.isEmpty()) {
                modeloUsuario.buscarUsuariosNickname(nickname, new CallBack<List<Usuario>>() {
                    @Override
                    public void onSuccess(List<Usuario> resultado) {
                        listaUsuarios.clear();
                        listaUsuarios.addAll(resultado);
                        adaptadorUsuario.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(AddAmigoActivity.this, error, Toast.LENGTH_SHORT).show();
                        listaUsuarios.clear();
                        adaptadorUsuario.notifyDataSetChanged();
                    }
                });
            } else {
                Toast.makeText(this, "Introduce un nickname", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
