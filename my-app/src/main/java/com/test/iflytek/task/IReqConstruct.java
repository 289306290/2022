package com.test.iflytek.task;

public interface IReqConstruct {
    String ConstructStartRequest();
    String ConstructDriveRequest(String session, String text, String ctrlW);
    String ConstructStopRequest(String session);
    String ConstructPingRequest(String session);
}
