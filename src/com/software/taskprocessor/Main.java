package com.software.taskprocessor;

public class Main {

    public static void main(String[] args) {

        TaskProcessor.start(5, 2000);

        TaskProcessor.addTask(new ClientTask());
    }
}
