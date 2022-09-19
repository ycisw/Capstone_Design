package com.example.capstonedesign.server.domain.network;

/**
 * 네트워크 로직 실패시 사용되는 로직을 구현하면 됩니다.
 * @param <T> 이 타입은 성공했을 때, 받아오는 결과의 타입입니다.
 */
@FunctionalInterface
public interface FailedLogic<T> {
    /**
     * 실패시 처리할 로직
     */
    void failedLogic(T response);
}
