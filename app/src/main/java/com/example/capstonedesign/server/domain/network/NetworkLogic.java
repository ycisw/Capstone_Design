package com.example.capstonedesign.server.domain.network;

public class NetworkLogic<T> {

    private SuccessLogic<T> successLogic;
    private FailedLogic failedLogic;

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
