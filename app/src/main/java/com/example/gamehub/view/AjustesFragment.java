package com.example.gamehub.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gamehub.R;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AjustesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AjustesFragment extends Fragment {

    public AjustesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false); // ojo aquí

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        Button btnCambiar = view.findViewById(R.id.chgpass_btn); // cambio a view
        TextInputEditText nuevaPassEdit = view.findViewById(R.id.newpass_et);
        SwitchMaterial switchMantenerSesion = view.findViewById(R.id.mantenersession_sw);

        SharedPreferences prefs = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        boolean mantenerSesion = prefs.getBoolean("mantener_sesion", false);
        switchMantenerSesion.setChecked(mantenerSesion);

        switchMantenerSesion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("mantener_sesion", isChecked);
            editor.apply();
        });

        btnCambiar.setOnClickListener(v -> {
            String nuevaContrasena = nuevaPassEdit.getText().toString().trim();

            if (nuevaContrasena.isEmpty() || nuevaContrasena.length() < 6) {
                nuevaPassEdit.setError("Debe tener al menos 6 caracteres");
                return;
            }

            if (user != null) {
                user.updatePassword(nuevaContrasena)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Contraseña actualizada", Toast.LENGTH_SHORT).show();
                                nuevaPassEdit.setText("");
                            } else {
                                Toast.makeText(getContext(), "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(getContext(), "Usuario no autenticado", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}