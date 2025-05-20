package com.example.gamehub.controller;

import com.example.gamehub.Utils.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String id_firebase;
    private String nickname;
    private String url_avatar;
    private LocalDateTime fecha_creacion;

    public Usuario(int id, String nombre, String email, String id_firebase, String nickname, String url_avatar, LocalDateTime fecha_creacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.id_firebase = id_firebase;
        this.nickname = nickname;
        this.url_avatar = url_avatar;
        this.fecha_creacion = fecha_creacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_firebase() {
        return id_firebase;
    }

    public void setId_firebase(String id_firebase) {
        this.id_firebase = id_firebase;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUrl_avatar() {
        return url_avatar;
    }

    public void setUrl_avatar(String url_avatar) {
        this.url_avatar = url_avatar;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public static Usuario fromJson(String json){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        return gson.fromJson(json, Usuario.class);
    }

    public String toString(){
        return "Usuario {" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", id_firebase='" + id_firebase + '\'' +
                ", nickname='" + nickname + '\'' +
                ", url_avatar='" + url_avatar + '\'' +
                ", fecha_creacion='" + fecha_creacion + '\'' +
                '}';
    }
}