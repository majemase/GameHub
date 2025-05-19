package com.example.gamehub.controller;

import java.time.LocalDateTime;

public class Juego {
    private int id;
    private int id_steam;
    private String nombre;
    private String descripcion;
    private String mig_url;
    private String genero;
    private String plataforma;
    private LocalDateTime fecha_lanzamiento;

    public Juego(int id, int id_steam, String nombre, String descripcion, String mig_url, String genero, String plataforma, LocalDateTime fecha_lanzamiento) {
        this.id = id;
        this.id_steam = id_steam;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.mig_url = mig_url;
        this.genero = genero;
        this.plataforma = plataforma;
        this.fecha_lanzamiento = fecha_lanzamiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_steam() {
        return id_steam;
    }

    public void setId_steam(int id_steam) {
        this.id_steam = id_steam;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMig_url() {
        return mig_url;
    }

    public void setMig_url(String mig_url) {
        this.mig_url = mig_url;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public LocalDateTime getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    public void setFecha_lanzamiento(LocalDateTime fecha_lanzamiento) {
        this.fecha_lanzamiento = fecha_lanzamiento;
    }
}
