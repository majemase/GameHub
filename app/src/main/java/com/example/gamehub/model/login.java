package com.example.gamehub.model;

import com.example.gamehub.view.LoginActivity;

public class login {
    private final LoginActivity view;
    private final FirebaseAuth auth;

    public Login(LoginActivity view){
        this.view = view;
        this.auth = FirebaseAuth.getInstance();
    }

    public void loginUsuario(){

    }
}
