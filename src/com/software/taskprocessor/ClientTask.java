package com.software.taskprocessor;

public class ClientTask implements Task {

    @Override
    public void execute() {
        try {
            onStart();
            //client implementation
            onSuccess();
        } catch (Exception e) {
            onFailure();
        }
    }

    @Override
    public String onStart() {
        //client implementation
        return "onStart";
    }

    @Override
    public String onSuccess() {
        //client implementation
        return "onSuccess";
    }

    @Override
    public String onFailure() {
        //client implementation
        return "onFailure";
    }
}
