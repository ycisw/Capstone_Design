package com.example.capstonedesign.server.domain.network;

/**
 * 네트워크 통신 성공시의 로직을 구현해주시면 됩니다.
 * @param <T> 이 타입은 성공했을 때, 받아오는 결과의 타입입니다.
 */
@FunctionalInterface
public interface SuccessLogic<T> {
    void successLogic(T response);
}
