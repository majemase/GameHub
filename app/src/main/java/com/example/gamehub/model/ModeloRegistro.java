package com.example.gamehub.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gamehub.controller.Usuario;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.Utils.Utilidades;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ModeloRegistro {
    private final Context context;
    private final FirebaseAuth auth;

    public ModeloRegistro(Context context){
        this.context = context;
        this.auth = FirebaseAuth.getInstance();
    }

    public void registrarUsuario(Usuario usuario, CallBack<Usuario> callback){
        auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getPassword())
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        String id_firebase = auth.getCurrentUser().getUid();
                        usuario.setId_firebase(id_firebase);

                        try {
                            enviarRegistroUsuario(usuario, callback);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        callback.onError(task.getException().getMessage());
                    }
                });
    }

    public void enviarRegistroUsuario(Usuario usuario, CallBack<Usuario> callback) throws JSONException {
        String url = Utilidades.getUrl(context) + "/usuario/registrarUsuario.php";
        JSONObject datosUsuario = new JSONObject(new Gson().toJson(usuario));

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, datosUsuario,
                response -> {
                    try {
                        if(response.getBoolean("success")){
                            callback.onSuccess(usuario);
                        } else {
                            callback.onError(response.getString("msg"));
                        }
                    } catch (Exception e) {
                        callback.onError(e.getMessage());
                    }
                },
                error -> callback.onError(error.getMessage())
        );

        Volley.newRequestQueue(context).add(request);
    }
}
