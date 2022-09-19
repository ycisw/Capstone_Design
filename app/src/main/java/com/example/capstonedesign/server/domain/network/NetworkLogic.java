package com.example.capstonedesign.server.domain.network;

/**
 * 네트워크(HTTP) 로직이 있을 경우
 * 성공했을 때와 실패했을 때의 로직을 구현해서 넣어주세요.
 * lambda식 혹은 익명 구현 객체, 혹은 클래스에 해당 인터페이스를 구현하여 해당 객체를 생성하여 주입해주시면 됩니다.
 * @param <T> 이 타입은 네트워크를 통해 받아오는 데이터 클래스 타입입니다. ex) LoginResult, TeacherAddResult...
 * ex) new NetworkLogic((loginResult) -> {성공시 로직}, () -> {실패시 로직}
 */
public class NetworkLogic<T> {

    private SuccessLogic<T> successLogic;
    private FailedLogic<T> failedLogic;

    /**
     * 성공했을때와 실패했을때의 로직을 넣어주세요.
     * @param successLogic 성공시받는결과 -> {성공했을때의 로직}
     * @param failedLogic () -> {실패했을때의 로직}
     */
    public NetworkLogic(SuccessLogic<T> successLogic, FailedLogic failedLogic) {
        this.successLogic = successLogic;
        this.failedLogic = failedLogic;
    }

    public SuccessLogic<T> getSuccessLogic() {
        return successLogic;
    }

    public FailedLogic getFailedLogic() {
        return failedLogic;
    }
}
