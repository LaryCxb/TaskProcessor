package com.software.taskprocessor;

import java.util.concurrent.LinkedBlockingQueue;

public class TaskProcessor {

    public static LinkedBlockingQueue<? super Task> linkedBlockingQueue = new LinkedBlockingQueue<>();

    public static void start() {
        Thread executor = new Thread(new Executor());
        executor.start();
    }

    public static void addTask(Task task) {
        linkedBlockingQueue.add(task);
    }
}
