package com.example.gamehub.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.Utils.Utilidades;
import com.example.gamehub.controller.Publicacion;
import com.example.gamehub.controller.Usuario;

import org.json.JSONArray;

import java.util.List;

public class ModeloHome {
    private final Context context;

    public ModeloHome(Context context) {
        this.context = context;
    }

    public void getAllPublicaciones(CallBack<List<Publicacion>> callBack){
        String url = Utilidades.getUrl(context) + "/publicaciones/getAllPublicaciones.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if(response.getBoolean("success")){
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
}
