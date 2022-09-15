package com.example.capstonedesign.server.domain.network;

/**
 * 네트워크 로직 실패시 사용되는 로직을 구현하면 됩니다.
 */
@FunctionalInterface
public interface FailedLogic {
    void failedLogic();
}
