package com.example.gamehub.controller;

import java.time.LocalDateTime;

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
}
