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

public class Publicacion {
    private int id;
    private Usuario autor;
    private int numLikes;
    private int numComentarios;
    private LocalDateTime fecha;
    private String texto;

    public Publicacion(int id, Usuario autor, int numLikes, int numComentarios, LocalDateTime fecha, String texto) {
        this.id = id;
        this.autor = autor;
        this.numLikes = numLikes;
        this.numComentarios = numComentarios;
        this.fecha = fecha;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumComentarios() {
        return numComentarios;
    }

    public void setNumComentarios(int numComentarios) {
        this.numComentarios = numComentarios;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public static Publicacion fromJson(String json){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        return gson.fromJson(json, Publicacion.class);
    }

    public static List<Publicacion> fromJsonArray(JSONArray json) throws JSONException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        Type listType = new TypeToken<List<Publicacion>>() {}.getType();
        return gson.fromJson(json.toString(), listType);
    }

    public String toString(){
        return "Usuario {" +
                "id='" + id + '\'' +
                ", autor='" + autor + '\'' +
                ", numLikes='" + numLikes + '\'' +
                ", numComentarios='" + numComentarios + '\'' +
                ", fecha='" + fecha + '\'' +
                ", texto='='" + texto + '\'' +
                '}';
    }
}
