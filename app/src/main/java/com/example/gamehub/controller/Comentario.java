package com.example.gamehub.controller;

import com.example.gamehub.Utils.LocalDateTimeAdapter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

public class Comentario {
    private int id;
    private Publicacion publicacion;
    private Usuario autor;
    private String contenido;
    private LocalDateTime fecha_creacion;

    public Comentario(int id, Publicacion publicacion, Usuario autor, String contenido, LocalDateTime fecha_creacion) {
        this.id = id;
        this.publicacion = publicacion;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha_creacion = fecha_creacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public static List<Comentario> fromJsonArray(JSONArray jsonArray) throws JSONException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        Type listType = new TypeToken<List<Comentario>>() {}.getType();
        return gson.fromJson(jsonArray.toString(), listType);
    }
}
