package com.example.gamehub.controller;

import java.time.LocalDateTime;
import java.util.List;

public class Chat {
    private int id;
    private String nombre;
    private boolean grupo;
    private LocalDateTime fecha_creacion;
    private List<Usuario> participantes;

    public Chat(int id, String nombre, boolean grupo, LocalDateTime fecha_creacion, List<Usuario> participantes) {
        this.id = id;
        this.nombre = nombre;
        this.grupo = grupo;
        this.fecha_creacion = fecha_creacion;
        this.participantes = participantes;
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

    public boolean isGrupo() {
        return grupo;
    }

    public void setGrupo(boolean grupo) {
        this.grupo = grupo;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }
}
