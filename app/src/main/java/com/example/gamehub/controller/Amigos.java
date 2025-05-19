package com.example.gamehub.controller;

import java.time.LocalDateTime;

public class Amigos {
    private int id_usuario1;
    private int id_usuario2;
    private Enum<AmigosStatus> estado;
    private LocalDateTime fecha_solicitud;

    public Amigos(int id_usuario1, int id_usuario2, Enum<AmigosStatus> estado, LocalDateTime fecha_solicitud) {
        this.id_usuario1 = id_usuario1;
        this.id_usuario2 = id_usuario2;
        this.estado = estado;
        this.fecha_solicitud = fecha_solicitud;
    }

    public int getId_usuario1() {
        return id_usuario1;
    }

    public void setId_usuario1(int id_usuario1) {
        this.id_usuario1 = id_usuario1;
    }

    public int getId_usuario2() {
        return id_usuario2;
    }

    public void setId_usuario2(int id_usuario2) {
        this.id_usuario2 = id_usuario2;
    }

    public Enum<AmigosStatus> getEstado() {
        return estado;
    }

    public void setEstado(Enum<AmigosStatus> estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(LocalDateTime fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }
}
