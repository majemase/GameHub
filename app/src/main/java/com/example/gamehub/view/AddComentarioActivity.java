package com.example.gamehub.view;

import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamehub.R;
import com.example.gamehub.Utils.AdaptadorComentario;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.controller.Comentario;
import com.example.gamehub.model.ModeloPublicacion;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class AddComentarioActivity extends AppCompatActivity {

    private ModeloPublicacion modeloPublicacion;
    private SharedPreferences preferences;
    private AdaptadorComentario adaptadorComentario;
    private List<Comentario> listaComentarios;
    private RecyclerView recyclerView;
    private EditText textComent_et;
    private String id_publicacion;
    private String id_firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_comentario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        modeloPublicacion = new ModeloPublicacion(this);
        preferences = getSharedPreferences("usuario", MODE_PRIVATE);

        id_publicacion = getIntent().getStringExtra("id_publicacion");
        id_firebase = preferences.getString("id_firebase", null);

        recyclerView = findViewById(R.id.comentarios_rv);
        textComent_et = findViewById(R.id.textComent_et);
        Button comentar_btn = findViewById(R.id.comentar_btn);

        listaComentarios = new ArrayList<>();
        adaptadorComentario = new AdaptadorComentario(listaComentarios, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorComentario);

        cargarComentarios();

        comentar_btn.setOnClickListener(v -> {
            String texto = textComent_et.getText().toString().trim();
            if (texto.isEmpty()) {
                Toast.makeText(this, "Escribe un comentario", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                modeloPublicacion.addComentario(id_firebase, id_publicacion, texto, new CallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean resultado) {
                        Toast.makeText(AddComentarioActivity.this, "Comentario enviado", Toast.LENGTH_SHORT).show();
                        textComent_et.setText("");
                        cargarComentarios();
                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(AddComentarioActivity.this, "Error: " + msg, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (JSONException e) {
                Log.e("Error", "JSONException: " + e.getMessage());
            }
        });
    }

    private void cargarComentarios() {
        modeloPublicacion.getComentarios(id_publicacion, new CallBack<List<Comentario>>() {
            @Override
            public void onSuccess(List<Comentario> comentarios) {
                listaComentarios.clear();
                listaComentarios.addAll(comentarios);
                adaptadorComentario.notifyDataSetChanged();
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(AddComentarioActivity.this, msg, Toast.LENGTH_SHORT).show();
                Log.e("Error", "Error al cargar comentarios: " + msg);
            }
        });
    }
}
