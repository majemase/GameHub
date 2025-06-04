package com.example.gamehub.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.Utils.Utilidades;
import com.example.gamehub.controller.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeloUsuario {
    private final Context context;
    private final FirebaseAuth auth;

    public ModeloUsuario(Context context){
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

    public void obtenerAmigos(String id_firebase, CallBack<List<Usuario>> callback){
        String url = Utilidades.getUrl(context) + "/usuario/getListadoAmigos.php?id_firebase="+id_firebase;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if(response.getBoolean("success")){
                            JSONArray data = response.getJSONArray("data");
                            List<Usuario> listaAmigos = new ArrayList<>();
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject obj = data.getJSONObject(i);
                                Usuario amigo = new Usuario(obj.getInt("id"), null, null, null, obj.getString("id_firebase"), obj.getString("nickname"), null, null);
                                listaAmigos.add(amigo);
                            }
                            callback.onSuccess(listaAmigos);
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

    public void buscarUsuariosNickname(String nickname, CallBack<List<Usuario>> callback) {
        String url = Utilidades.getUrl(context) + "/usuario/buscarUsuario.php?nickname=" + nickname;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            JSONArray data = response.getJSONArray("data");
                            List<Usuario> listaEncontrados = new ArrayList<>();
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject obj = data.getJSONObject(i);
                                Usuario usuario = new Usuario(
                                        obj.getInt("id"),
                                        null, null, null,
                                        obj.getString("id_firebase"),
                                        obj.getString("nickname"),
                                        null, null
                                );
                                listaEncontrados.add(usuario);
                            }
                            callback.onSuccess(listaEncontrados);
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

    public void enviarSolicitudAmistad(String id_firebase1, String id_firebase2, CallBack<String> callback) {
        String url = Utilidades.getUrl(context) + "/usuario/addAmigo.php";

        Map<String, String> params = new HashMap<>();
        params.put("id_usuario1", id_firebase1);
        params.put("id_usuario2", id_firebase2);
        params.put("accion", "enviar");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            callback.onSuccess(response.getString("msg"));
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

    public void guardarUsuario(Usuario usuario){
        SharedPreferences preferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nickname", usuario.getNickname());
        editor.putString("id_firebase", usuario.getId_firebase());
        editor.putString("email", usuario.getEmail());
        editor.commit();
    }
}
