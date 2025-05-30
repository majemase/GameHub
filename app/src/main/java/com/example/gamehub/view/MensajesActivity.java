package com.example.gamehub.view;

import android.os.Bundle;
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
import com.example.gamehub.Utils.AdaptadorMensajes;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.controller.Mensaje;
import com.example.gamehub.model.ModeloMensaje;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class MensajesActivity extends AppCompatActivity {
    private RecyclerView mensajes_rv;
    private AdaptadorMensajes adapter;
    private EditText mensaje_et;
    private MaterialButton enviar_btn;
    private ModeloMensaje modeloMensaje;
    private String idChat;
    private String id_firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mensajes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mensajes_rv = findViewById(R.id.mensajes_rv);
        mensaje_et = findViewById(R.id.mensaje_et);
        enviar_btn = findViewById(R.id.enviar_btn);

        id_firebase = FirebaseAuth.getInstance().getCurrentUser().getUid();
        idChat = getIntent().getStringExtra("id_chat");
        modeloMensaje = new ModeloMensaje();

        adapter = new AdaptadorMensajes(id_firebase);
        mensajes_rv.setAdapter(adapter);
        mensajes_rv.setLayoutManager(new LinearLayoutManager(this));

        enviar_btn.setOnClickListener(v -> {
            String texto = mensaje_et.getText().toString().trim();
            if (!texto.isEmpty()) {
                modeloMensaje.enviarMensaje(idChat, id_firebase, texto, new CallBack<Void>() {
                    @Override
                    public void onSuccess(Void resultado) {
                        mensaje_et.setText("");
                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(MensajesActivity.this, "Error al enviar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        modeloMensaje.escucharMensajes(idChat, new CallBack<List<Mensaje>>() {
            @Override
            public void onSuccess(List<Mensaje> resultado) {
                adapter.setDataset(resultado);
                mensajes_rv.scrollToPosition(resultado.size() - 1);
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(MensajesActivity.this, "Error: " + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        modeloMensaje.detenerEscuchas(idChat);
    }
}