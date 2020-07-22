package com.software.taskprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueExecutor implements Runnable {

    private final int repetitions;
    private final long sleepTime;
    Logger logger = LoggerFactory.getLogger(getClass());

    public QueueExecutor(int repetitions, long sleepTime) {
        this.repetitions = repetitions;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = (Task) TaskProcessor.getLinkedBlockingQueue().take();
                execute(task);
            } catch (InterruptedException e) {
                logger.warn("Waiting for task interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }

    private void execute(Task task) {
        Thread executor = new Thread(new TaskExecutor(task, repetitions, sleepTime, 0));
        executor.start();
    }
}
