package com.example.gamehub.model;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gamehub.Utils.CallBack;
import com.example.gamehub.controller.Mensaje;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModeloMensaje {
    private final DatabaseReference refMensaje;
    private ValueEventListener mensajeListener;

    public ModeloMensaje() {
        this.refMensaje = FirebaseDatabase.getInstance("https://gamehub-2b697-default-rtdb.europe-west1.firebasedatabase.app/").getReference("mensajes");
    }

    public void enviarMensaje(String idChat, String idAutor, String contenido, CallBack<Void> callBack) {
        String id = refMensaje.child(idChat).push().getKey();
        Mensaje mensaje = new Mensaje(idChat, idAutor, contenido, System.currentTimeMillis());

        refMensaje.child(idChat).child(id).setValue(mensaje)
                .addOnSuccessListener(unused -> callBack.onSuccess(null))
                .addOnFailureListener(e -> callBack.onError(e.getMessage()));
    }

    public void escucharMensajes(String idChat, CallBack<List<Mensaje>> callBack) {
        mensajeListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Mensaje> listaMensajes = new ArrayList<>();
                for (DataSnapshot snap :
                        snapshot.getChildren()) {
                    Mensaje m = snap.getValue(Mensaje.class);
                    listaMensajes.add(m);
                }
                callBack.onSuccess(listaMensajes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callBack.onError(error.getMessage());
            }
        };
        refMensaje.child(idChat).addValueEventListener(mensajeListener);
    }

    public void escucharNotificaciones(String idChat, long ultimoLeido, CallBack<Mensaje> callBack) {
        refMensaje.child(idChat).orderByChild("timestamp").startAt(ultimoLeido + 1)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Mensaje nuevo = snapshot.getValue(Mensaje.class);
                        if (nuevo != null) {
                            callBack.onSuccess(nuevo);
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callBack.onError(error.getMessage());
                    }
                });
    }

    public void detenerEscuchas(String idChat) {
        if(mensajeListener != null){
            refMensaje.child(idChat).removeEventListener(mensajeListener);
            mensajeListener = null;
        }
    }
}
