package com.example.gamehub.controller;

import java.time.LocalDateTime;

public class Transaccion {
    private int id;
    private Anuncio anuncio;
    private Usuario comprador;
    private LocalDateTime fecha_compra;
    private Enum<TransaccionStatus> estado;

    public Transaccion(int id, Anuncio anuncio, Usuario comprador, LocalDateTime fecha_compra, Enum<TransaccionStatus> estado) {
        this.id = id;
        this.anuncio = anuncio;
        this.comprador = comprador;
        this.fecha_compra = fecha_compra;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public LocalDateTime getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(LocalDateTime fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Enum<TransaccionStatus> getEstado() {
        return estado;
    }

    public void setEstado(Enum<TransaccionStatus> estado) {
        this.estado = estado;
    }
}
