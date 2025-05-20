package com.example.gamehub.Utils;

public interface CallBack<T> {
    void onSuccess(T resultado);
    void onError(String msg);
}
