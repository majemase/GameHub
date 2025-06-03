package com.example.gamehub.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.Utils.Utilidades;
import com.example.gamehub.controller.Comentario;
import com.example.gamehub.controller.Publicacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ModeloPublicacion {
    private final Context context;

    public ModeloPublicacion(Context context) {
        this.context = context;
    }

    public void getAllPublicaciones(CallBack<List<Publicacion>> callBack) {
        String url = Utilidades.getUrl(context) + "/publicaciones/getAllPublicaciones.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            JSONArray data = response.getJSONArray("data");
                            List<Publicacion> posts = Publicacion.fromJsonArray(data);
                            callBack.onSuccess(posts);
                        } else {
                            callBack.onError(response.getString("msg"));
                        }
                    } catch (Exception e) {
                        callBack.onError(e.getMessage());
                    }
                },
                error -> callBack.onError(error.getMessage())
        );

        Volley.newRequestQueue(context).add(request);
    }

    public void darLike(String id_firebase, int id_publicacion, CallBack<Boolean> callBack) {
        String url = Utilidades.getUrl(context) + "/publicaciones/darLike.php?id_firebase=" + id_firebase + "&id_publicacion=" + id_publicacion;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            callBack.onSuccess(response.getBoolean("data"));
                        } else {
                            callBack.onError(response.getString("msg"));
                        }
                    } catch (Exception e) {
                        callBack.onError(e.getMessage());
                    }
                },
                error -> callBack.onError(error.getMessage())
        );

        Volley.newRequestQueue(context).add(request);
    }

    public void getPublicacionesUsuario(String id_firebase, CallBack<List<Publicacion>> callBack) {
        String url = Utilidades.getUrl(context) + "/publicaciones/getPublicacionesUsuario.php?id_firebase=" + id_firebase;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            JSONArray data = response.getJSONArray("data");
                            List<Publicacion> posts = Publicacion.fromJsonArray(data);
                            callBack.onSuccess(posts);
                        } else {
                            callBack.onError(response.getString("msg"));
                        }
                    } catch (Exception e) {
                        callBack.onError(e.getMessage());
                    }
                },
                error -> callBack.onError(error.getMessage())
        );

        Volley.newRequestQueue(context).add(request);
    }

    public void getPubLikesUsuario(String id_firebase, CallBack<List<Publicacion>> callBack) {
        String url = Utilidades.getUrl(context) + "/publicaciones/getPubLikesUsuario.php?id_firebase=" + id_firebase;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            JSONArray data = response.getJSONArray("data");
                            List<Publicacion> posts = Publicacion.fromJsonArray(data);
                            callBack.onSuccess(posts);
                        } else {
                            callBack.onError(response.getString("msg"));
                        }
                    } catch (Exception e) {
                        callBack.onError(e.getMessage());
                    }
                },
                error -> callBack.onError(error.getMessage())
        );

        Volley.newRequestQueue(context).add(request);
    }

    public void addPublicacion(String id_firebase, String texto, CallBack<Boolean> callBack) throws JSONException {
        String url = Utilidades.getUrl(context) + "/publicaciones/addPublicacion.php";

        JSONObject datosPublicacion = new JSONObject();
        datosPublicacion.put("id_firebase", id_firebase);
        datosPublicacion.put("contenido", texto);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, datosPublicacion,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            callBack.onSuccess(response.getBoolean("success"));
                        } else {
                            callBack.onError(response.getString("msg"));
                        }
                    } catch (Exception e) {
                        callBack.onError(e.getMessage());
                    }
                },
                error -> callBack.onError(error.getMessage())
        );

        Volley.newRequestQueue(context).add(request);
    }

    public void addComentario(String id_firebase, String id_publicacion, String texto, CallBack<Boolean> callBack) throws JSONException {
        String url = Utilidades.getUrl(context) + "/publicaciones/addComentario.php";

        JSONObject datosComentario = new JSONObject();
        datosComentario.put("id_firebase", id_firebase);
        datosComentario.put("id_publicacion", id_publicacion);
        datosComentario.put("contenido", texto);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, datosComentario,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            callBack.onSuccess(response.getBoolean("success"));
                        } else {
                            callBack.onError(response.getString("msg"));
                        }
                    } catch (Exception e) {
                        callBack.onError(e.getMessage());
                    }
                },
                error -> callBack.onError(error.getMessage())
        );

        Volley.newRequestQueue(context).add(request);
    }

    public void getComentarios(String id_publicacion, CallBack<List<Comentario>> callBack) {
        String url = Utilidades.getUrl(context) + "/publicaciones/getComentarios.php?id_publicacion=" + id_publicacion;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            JSONArray data = response.getJSONArray("data");
                            List<Comentario> comentarios = Comentario.fromJsonArray(data);
                            callBack.onSuccess(comentarios);
                        } else {
                            callBack.onError(response.getString("msg"));
                        }
                    } catch (Exception e) {
                        callBack.onError(e.getMessage());
                    }
                },
                error -> callBack.onError(error.getMessage())
        );

        Volley.newRequestQueue(context).add(request);
    }

}
