package com.example.capstonedesign.server.domain.network;

@FunctionalInterface
public interface SuccessLogic<T> {
    void successLogic(T response);
}
