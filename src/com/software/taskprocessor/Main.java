package com.software.taskprocessor;

public class Main {

    public static void main(String[] args) {

        TaskProcessor.start();

        TaskProcessor.addTask(new ClientTask());
    }
}
