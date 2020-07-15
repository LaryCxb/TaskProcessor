package com.software.taskprocessor;

import java.util.concurrent.LinkedBlockingQueue;

public class TaskProcessor {

    private static final LinkedBlockingQueue<? super Task> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private TaskProcessor() {
    }

    public static void start(int repetitions, long sleepTime) {
        Thread executor = new Thread(new Executor(repetitions, sleepTime));
        executor.start();
    }

    public static void addTask(Task task) {
        linkedBlockingQueue.add(task);
    }

    public static LinkedBlockingQueue<? super Task> getLinkedBlockingQueue() {
        return linkedBlockingQueue;
    }
}
