package com.software.taskprocessor;

public class Main {

    public static void main(String[] args) {

        TaskProcessor.addTask(new Task() {
            @Override
            public boolean execute() {
                //implementation
                return true;
            }
        });
        TaskProcessor.start();
    }
}
