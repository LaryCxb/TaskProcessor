package com.software.taskprocessor;

public interface Task extends Callback {

    void execute() throws Exception;

    @Override
    public void onStart();

    @Override
    public void onSuccess();

    @Override
    public void onFailure();

}
