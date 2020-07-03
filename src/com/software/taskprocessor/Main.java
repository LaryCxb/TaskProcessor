package com.software.taskprocessor;

public class Main {

    public static void main(String[] args) {

        TaskProcessor.start(5, 1000);

        TaskProcessor.addTask(new ClientTask());
    }
}
