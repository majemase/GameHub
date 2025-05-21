package com.example.gamehub.model;

import android.app.Activity;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.Utils.Utilidades;
import com.example.gamehub.controller.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

public class ModeloLogin {
    private final Context context;
    private final FirebaseAuth auth;

    public ModeloLogin(Context context){
        this.context = context;
        this.auth = FirebaseAuth.getInstance();
    }

    public void loginUsuario(String email, String pass, CallBack<Usuario> callback){
        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener((Activity) context, task -> {
                    if(task.isSuccessful()){
                        FirebaseUser user = auth.getCurrentUser();
                        recogerDatosUsuario(user.getUid(), callback);
                    } else {
                        callback.onError(task.getException().getMessage());
                    }
                });
    }

    public void recogerDatosUsuario(String id_firebase, CallBack<Usuario> callback){
        String url = Utilidades.getUrl(context) + "/usuario/getDatosUsuario.php?id=" + id_firebase;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if(response.getBoolean("success")){
                            JSONObject data = response.getJSONObject("data");
                            Usuario usuario = Usuario.fromJson(data.toString());
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
