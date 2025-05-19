package com.example.gamehub.controller;

import java.time.LocalDateTime;

public class Mensaje {
    private int id;
    private Chat chat;
    private Usuario autor;
    private String contenido;
    private LocalDateTime fecha_envio;

    public Mensaje(int id, Chat chat, Usuario autor, String contenido, LocalDateTime fecha_envio) {
        this.id = id;
        this.chat = chat;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha_envio = fecha_envio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
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

    public LocalDateTime getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(LocalDateTime fecha_envio) {
        this.fecha_envio = fecha_envio;
    }
}
