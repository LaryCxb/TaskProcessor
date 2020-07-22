package com.software.taskprocessor.test;

import com.software.taskprocessor.Task;

public class FakedTask implements Task {

    @Override
    public void execute() throws Exception {

    }

    @Override
    public int updateErrorNumber(int errorNumber) {
        return 0;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }
}
