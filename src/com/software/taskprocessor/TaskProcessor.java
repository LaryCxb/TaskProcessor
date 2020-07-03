package com.software.taskprocessor;

import java.util.concurrent.LinkedBlockingQueue;

public class TaskProcessor {

    public static LinkedBlockingQueue<Task> linkedBlockingQueue = new LinkedBlockingQueue<>();

    public static void addTask(Task task) {
        linkedBlockingQueue.add(task);
    }

    public static void start() {
        Thread executor = new Thread(new Executor());
        executor.start();
    }
}
