package com.example.gamehub.Utils;

public class UtilidadesChat {
    public static String generarIdChat(String id_firebase1, String id_firebase2){
        return id_firebase1.compareTo(id_firebase2) < 0 ? id_firebase1 + "_" + id_firebase2 : id_firebase2 + "_" + id_firebase1;
    }
}
