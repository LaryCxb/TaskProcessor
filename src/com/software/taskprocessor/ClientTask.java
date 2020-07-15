package com.software.taskprocessor;

public class ClientTask implements Task {

    String name;
    int errorNumber = 0;

    public ClientTask(String name) {
        this.name = name;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("TASK EXECUTION");
        throw new Exception("EXCEPTION");
    }

    @Override
    public int updateErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
        return errorNumber;
    }

    @Override
    public void onStart() {
        System.out.println("START " + name);
    }

    @Override
    public void onSuccess() {
        System.out.println("SUCCESS " + name);
    }

    @Override
    public void onFailure() {
        System.out.println("FAILURE #" + errorNumber + " " + name + "\n");
    }
}
