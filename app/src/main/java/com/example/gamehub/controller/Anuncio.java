package com.example.gamehub.controller;

import java.time.LocalDateTime;

public class Anuncio {
    private int id;
    private Usuario vendedor;
    private Juego juego;
    private double precio;
    private Enum<AnuncioStatus> estado;
    private String descripcion;
    private LocalDateTime fecha;
}
