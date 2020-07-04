package com.software.taskprocessor;

public class Main {

    public static void main(String[] args) {

        TaskProcessor.start(2, 1000);

        TaskProcessor.addTask(new ClientTask("name1"));
        TaskProcessor.addTask(new ClientTask("name2"));

    }
}
