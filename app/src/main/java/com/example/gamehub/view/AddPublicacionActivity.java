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

import com.example.gamehub.R;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.model.ModeloPublicacion;

import org.json.JSONException;

public class AddPublicacionActivity extends AppCompatActivity {

    private ModeloPublicacion modeloPublicacion;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_publicacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText textPubli_et = findViewById(R.id.textPubli_et);
        Button publicar_btn = findViewById(R.id.publicar_btn);

        modeloPublicacion = new ModeloPublicacion(this);
        preferences = getSharedPreferences("usuario", MODE_PRIVATE);
        String id_firebase = preferences.getString("id_firebase", "");


        publicar_btn.setOnClickListener(v -> {
            if (textPubli_et.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Debes escribir algo en la publicacion", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                modeloPublicacion.addPublicacion(id_firebase, textPubli_et.getText().toString(), new CallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean resultado) {
                        Toast.makeText(AddPublicacionActivity.this, "Has añadido una publicacion", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(AddPublicacionActivity.this, "Ha ocurrido un error al publicar", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (JSONException e) {
                Log.e("Error", "Error al enviar la publicacion: " + e.getMessage());
            }
        });
    }
}