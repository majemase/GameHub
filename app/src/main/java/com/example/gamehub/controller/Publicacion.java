package com.example.gamehub.controller;

import java.time.LocalDateTime;

public class Publicacion {
    private int id;
    private Usuario autor;
    private LocalDateTime fecha;
    private String texto;

    public Publicacion(int id, Usuario autor, LocalDateTime fecha, String texto) {
        this.id = id;
        this.autor = autor;
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
}
