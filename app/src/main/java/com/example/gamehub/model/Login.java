package com.example.gamehub.model;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.Utils.Utilidades;
import com.example.gamehub.controller.Usuario;
import com.example.gamehub.view.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

public class Login {
    private final LoginActivity view;
    private final FirebaseAuth auth;

    public Login(LoginActivity view){
        this.view = view;
        this.auth = FirebaseAuth.getInstance();
    }

    public void loginUsuario(String email, String pass){
        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(view, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser usuario = auth.getCurrentUser();
                            if(usuario != null){
                                recogerDatosUsuario(usuario.getUid(), new CallBack<Usuario>() {

                                    @Override
                                    public void onSuccess(Usuario usuario) {
                                        view.loginCorrecto(usuario);
                                    }

                                    @Override
                                    public void onError(String msg) {
                                        view.loginIncorrecto(msg);
                                    }
                                });
                            }
                        }else{
                            view.loginIncorrecto(task.getException().getMessage());
                        }
                    }
                });
    }

    public void recogerDatosUsuario(String id_firebase, CallBack<Usuario> callBack){
        String url = Utilidades.getUrl(view) + "/usuario/getDatosUsuario.php?id=" + id_firebase;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if(response.getBoolean("success")){
                            JSONObject data = response.getJSONObject("data");
                            Usuario usuario = Usuario.fromJson(data.toString());
                            callBack.onSuccess(usuario);
                        }else{
                            callBack.onError(response.getString("msg"));
                        }
                    } catch (Exception e) {
                        callBack.onError(e.getMessage());
                    }
                },
                error -> callBack.onError(error.getMessage()));

        Volley.newRequestQueue(view).add(request);
    }
}
