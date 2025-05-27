package com.example.gamehub.controller;

public class Mensaje {
    private String idChat;
    private String idAutor;
    private String contenido;
    private long timestamp;

    public Mensaje() {}

    public Mensaje(String idChat, String idAutor, String contenido, long timestamp) {
        this.idChat = idChat;
        this.idAutor = idAutor;
        this.contenido = contenido;
        this.timestamp = timestamp;
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
