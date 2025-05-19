package com.example.gamehub.controller;

import java.time.LocalDateTime;

public class Like {
    private int id;
    private Publicacion publicacion;
    private Usuario usuario;
    private LocalDateTime fecha;

    public Like(int id, Publicacion publicacion, Usuario usuario, LocalDateTime fecha) {
        this.id = id;
        this.publicacion = publicacion;
        this.usuario = usuario;
        this.fecha = fecha;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
